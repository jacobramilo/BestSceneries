package com.jacob.bestsceneries.repository

import androidx.lifecycle.LiveData
import com.google.android.gms.maps.model.LatLng
import com.jacob.bestsceneries.api.SceneriesWebservice
import com.jacob.bestsceneries.database.dao.SceneriesDao
import com.jacob.bestsceneries.database.entity.Scenery
import com.jacob.bestsceneries.model.Sceneries
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import javax.inject.Inject

class SceneriesRepository @Inject constructor(
    private val sceneryApi: SceneriesWebservice,
    private val sceneryDao: SceneriesDao,
    private val executor: Executor
) {

    fun loadSceneries(): LiveData<List<Scenery>> {
        refreshScenery()
        return sceneryDao.getSceneries()
    }

    fun saveScenery(scenery: Scenery) {
        executor.execute {
            sceneryDao.save(scenery)
        }
    }

    fun getScenery(latLng: LatLng): LiveData<Scenery> {
        return sceneryDao.getScenery(latLng.latitude, latLng.longitude)
    }

    fun getScenery(id: Int): LiveData<Scenery> {
        return sceneryDao.getScenery(id)
    }

    private fun refreshScenery() {
        executor.execute {
            sceneryApi.getScenery().enqueue(object: Callback<Sceneries> {
                override fun onFailure(call: Call<Sceneries>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<Sceneries>, response: Response<Sceneries>) {
                    executor.execute {
                        val sceneries = response.body()
                        sceneries?.locations?.forEach {
                            sceneryDao.save(it)
                        }
                    }
                }
            })
        }
    }
}