package com.jacob.bestsceneries

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.google.android.gms.maps.model.LatLng
import com.jacob.bestsceneries.database.entity.Scenery
import com.jacob.bestsceneries.database.entity.SceneryNote
import com.jacob.bestsceneries.util.Constant
import com.jacob.bestsceneries.viewmodel.SceneryViewModel
import kotlinx.android.synthetic.main.activity_scenery_details.*
import javax.inject.Inject

class SceneryDetailsActivity: AppCompatActivity() {

    private var scenery: Scenery? = null
    private var sceneryNote: SceneryNote? = null

    @Inject lateinit var viewModel: SceneryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_scenery_details)

        (application as MyApplication).appComponent.inject(this)

        initUi()
    }

    private fun initUi() {
        val lat = intent?.getDoubleExtra(Constant.PARAM_EXTRA_SCENERY_LAT, 0.0)
        val lng = intent?.getDoubleExtra(Constant.PARAM_EXTRA_SCENERY_LONG, 0.0)
        val name = intent?.getStringExtra(Constant.PARAM_EXTRA_SCENERY_NAME)

        supportActionBar?.title = name

        viewModel.getScenery(lat, lng)?.observe(this, Observer {
            scenery = it
            updateUI(it)
        })

    }

    private fun updateUI(scenery: Scenery) {
        tv_scenery_name.text = scenery.name
        tv_scenery_lat.text = scenery.lat.toString()
        tv_longitude.text = scenery.lng.toString()

        viewModel.getNote(scenery.noteId).observe(this, Observer {
            sceneryNote
            tv_notes.text = it.note
        })

        invalidateOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.scenery_detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        var menuTitle = getString(R.string.add_note)
        if(!TextUtils.isEmpty(sceneryNote?.note)) {
            menuTitle = getString(R.string.edit_note)
        }

        val menuItem = menu?.findItem(R.id.notes)
        menuItem?.title = menuTitle

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.notes -> {
                addNote()
            }
        }

        return super.onOptionsItemSelected(item)
    }
    private fun addNote() {
        MaterialDialog(this).show {
            title(R.string.enter_scenery_note)
            input(prefill = sceneryNote?.note) { dialog, text ->
                sceneryNote?.let {
                    it.note = text.toString()
                    viewModel.saveNote(it)
                }
            }
            positiveButton(R.string.submit)
        }
    }
}