package com.jacob.bestsceneries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.jacob.bestsceneries.database.entity.Scenery
import com.jacob.bestsceneries.repository.SceneriesRepository

class SceneryViewModel constructor(
    private val sceneryRepository: SceneriesRepository
) : ViewModel() {

    fun getSceneries(): LiveData<List<Scenery>> {
        return sceneryRepository.loadSceneries()
    }

    fun saveScenery(scenery: Scenery) {
        sceneryRepository.saveScenery(scenery)
    }

    fun getScenery(lat: Double, lng: Double): LiveData<Scenery> {
        return sceneryRepository.getScenery(lat, lng)
    }

    fun getSydneyLatLong(): LatLng {
        return LatLng(-33.86785, 151.20732)
    }
}