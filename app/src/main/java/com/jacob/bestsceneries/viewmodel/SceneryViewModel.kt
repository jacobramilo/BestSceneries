package com.jacob.bestsceneries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
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
}