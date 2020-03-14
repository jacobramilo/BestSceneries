package com.jacob.bestsceneries.util

import android.content.Context
import android.content.Intent
import com.jacob.bestsceneries.SceneryDetailsActivity

class Navigator {

    companion object {

        fun navigateToDetails(context: Context, name: String?, lat: Double, lng: Double) {
            val intent = Intent(context, SceneryDetailsActivity::class.java)
            intent.putExtra(Constant.PARAM_EXTRA_SCENERY_LAT, lat)
            intent.putExtra(Constant.PARAM_EXTRA_SCENERY_LONG, lng)
            intent.putExtra(Constant.PARAM_EXTRA_SCENERY_NAME, name)
            context.startActivity(intent)
        }

    }

}