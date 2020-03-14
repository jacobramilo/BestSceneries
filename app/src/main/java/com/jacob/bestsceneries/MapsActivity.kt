package com.jacob.bestsceneries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.maps.SupportMapFragment
import com.jacob.bestsceneries.fragment.SceneryListFragment
import com.jacob.bestsceneries.fragment.SceneryMapFragment
import com.jacob.bestsceneries.viewmodel.SceneryViewModel
import javax.inject.Inject

class MapsActivity : AppCompatActivity() {

    private var isMapView = true
    private var mapViewFragment: SupportMapFragment? = null
    private var listViewFragment: SceneryListFragment? = null

    @Inject lateinit var mViewModel: SceneryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        (application as MyApplication).appComponent.inject(this)

        reloadViews()
    }

    private fun reloadViews() {
        if(isMapView) {
            showMapsView()
        } else {
            showListView()
        }
    }

    private fun showListView() {
        if(listViewFragment == null) {
            listViewFragment = SceneryListFragment()
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_scenery, listViewFragment!!).commit()
    }

    private fun showMapsView() {
        if(mapViewFragment == null) {
            mapViewFragment = SceneryMapFragment()
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_scenery, mapViewFragment!!).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.scenery_main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.toggle -> {
                toggle()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        var menuTitle = getString(R.string.map_view)
        if(isMapView) {
           menuTitle = getString(R.string.list_view)
        }

        menu?.findItem(R.id.toggle)?.title = menuTitle
        return super.onPrepareOptionsMenu(menu)
    }

    private fun toggle() {
        isMapView = !isMapView
        reloadViews()
        invalidateOptionsMenu()
    }

}
