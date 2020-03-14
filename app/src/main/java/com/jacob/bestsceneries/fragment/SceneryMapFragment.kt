package com.jacob.bestsceneries.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.jacob.bestsceneries.MapsActivity
import com.jacob.bestsceneries.MyApplication
import com.jacob.bestsceneries.R
import com.jacob.bestsceneries.SceneryDetailsActivity
import com.jacob.bestsceneries.database.entity.Scenery
import com.jacob.bestsceneries.util.Constant
import com.jacob.bestsceneries.viewmodel.SceneryViewModel
import javax.inject.Inject
import androidx.lifecycle.Observer

class SceneryMapFragment: SupportMapFragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var mMap: GoogleMap

    @Inject
    lateinit var mViewModel: SceneryViewModel

    override fun onCreate(p0: Bundle?) {
        super.onCreate(p0)

        val application = (activity as MapsActivity).application as MyApplication
        application.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMapAsync(this)
    }

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

    private fun updateMapView(sceneries: List<Scenery>) {
        if(sceneries.isEmpty()) {
            return
        }
        sceneries.forEachIndexed { index, scenery ->
            val lastlatLng = LatLng(scenery.lat, scenery.lng)
            mMap.addMarker(
                MarkerOptions().position(lastlatLng)
                    .title(scenery.name)
            ).tag = lastlatLng
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mViewModel.getSydneyLatLong(), 12.0f))
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val intent = Intent(activity, SceneryDetailsActivity::class.java)
        intent.putExtra(Constant.PARAM_EXTRA_SCENERY_ID, marker.tag as LatLng)
        startActivity(intent)
        return true
    }

    private fun showAddLocation(latLng: LatLng) {
        MaterialDialog(context!!).show {
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
}