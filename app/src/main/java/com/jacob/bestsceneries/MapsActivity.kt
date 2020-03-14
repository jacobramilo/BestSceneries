package com.jacob.bestsceneries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.jacob.bestsceneries.database.entity.Scenery
import com.jacob.bestsceneries.util.Constant
import com.jacob.bestsceneries.viewmodel.SceneryViewModel
import javax.inject.Inject

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap

    @Inject lateinit var mViewModel: SceneryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        (application as MyApplication).appComponent.inject(this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener { latLng ->
            showAddLocation(latLng)
        }
        mMap.setOnMarkerClickListener(this)

        mViewModel.getSceneries().observe(this, Observer { sceneries ->
            updateMapView(sceneries)
        })
    }

    private fun showAddLocation(latLng: LatLng) {
        MaterialDialog(this).show {
            title(R.string.enter_scenery_name)
            input { dialog, text ->
                val scenery = Scenery()
                scenery.name = text.toString()
                scenery.lng = latLng.longitude
                scenery.lat = latLng.latitude
                mViewModel.saveScenery(scenery)
            }
            positiveButton(R.string.submit)
        }
    }

    private fun updateMapView(sceneries: List<Scenery>) {
        sceneries.forEachIndexed { index, scenery ->
            val latLng = LatLng(scenery.lat, scenery.lng)
            mMap.addMarker(
                MarkerOptions().position(latLng)
                    .title(scenery.name)
            ).tag = scenery.id
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f))
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val intent = Intent(this, SceneryDetailsActivity::class.java)
        intent.putExtra(Constant.PARAM_EXTRA_SCENERY_ID, marker.tag as Int)
        startActivity(intent)
        return true
    }
}
