package com.jacob.bestsceneries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jacob.bestsceneries.database.entity.Scenery
import com.jacob.bestsceneries.viewmodel.SceneryViewModel
import javax.inject.Inject

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

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

        mViewModel.getSceneries().observe(this, Observer { sceneries ->
            updateMapView(sceneries)
        })
    }

    private fun updateMapView(sceneries: List<Scenery>) {
        sceneries.forEach {
            val scenery = LatLng(it.lat, it.lng)
            mMap.addMarker(
                MarkerOptions().position(scenery)
                    .title(it.name)
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(scenery, 12.0f))
        }
    }
}
