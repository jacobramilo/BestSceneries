package com.jacob.bestsceneries.fragment

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jacob.bestsceneries.MapsActivity
import com.jacob.bestsceneries.MyApplication
import com.jacob.bestsceneries.R
import com.jacob.bestsceneries.database.entity.Scenery
import com.jacob.bestsceneries.util.Navigator
import com.jacob.bestsceneries.viewmodel.SceneryViewModel
import kotlinx.android.synthetic.main.fragment_scenery_list.*
import java.util.*
import javax.inject.Inject

class SceneryListFragment: Fragment() {

    @Inject lateinit var sceneryViewModel: SceneryViewModel

    lateinit var sceneryList: List<Scenery>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val application = (activity as MapsActivity).application as MyApplication
        application.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sceneryViewModel.getSceneries().observe(this, Observer {
            sceneryList = it

            val sydneyLatLng = sceneryViewModel.getSydneyLatLong()
            Collections.sort(it) { t, t2 ->

                val result1 = FloatArray(3)
                Location.distanceBetween(sydneyLatLng.latitude, sydneyLatLng.longitude, t.lat, t.lng, result1)
                val distance1 = result1[0]

                val result2 = FloatArray(3)
                Location.distanceBetween(sydneyLatLng.latitude, sydneyLatLng.longitude, t2.lat, t2.lng, result2)
                val distance2 = result2[0]

                distance1.compareTo(distance2)
            }

            val adapter = ArrayAdapter<Scenery>(context!!,  android.R.layout.simple_list_item_1, android.R.id.text1, it)
            scenery_list.adapter = adapter
        })

        scenery_list.setOnItemClickListener { _, _, pos, _ ->
            val scenery = sceneryList[pos]
            Navigator.navigateToDetails(context!!, scenery.name, scenery.lat, scenery.lng)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scenery_list, container, false)
    }
}