package com.jacob.bestsceneries.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jacob.bestsceneries.database.entity.Scenery

@Dao
interface SceneriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(scenery: Scenery)

    @Query(value = "SELECT * FROM Scenery")
    fun getSceneries(): LiveData<List<Scenery>>
}