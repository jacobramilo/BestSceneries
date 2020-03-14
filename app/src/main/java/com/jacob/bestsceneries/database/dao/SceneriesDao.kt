package com.jacob.bestsceneries.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jacob.bestsceneries.database.entity.Scenery
import com.jacob.bestsceneries.database.entity.SceneryNote

@Dao
interface SceneriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(scenery: Scenery)

    @Query(value = "SELECT * FROM Scenery")
    fun getSceneries(): LiveData<List<Scenery>>

    @Query(value = "SELECT * FROM Scenery WHERE lat = :lat and lng = :lng")
    fun getScenery(lat: Double, lng: Double): LiveData<Scenery>

    //Notes
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(sceneryNote: SceneryNote)

    @Query(value = "SELECT * FROM SceneryNote WHERE noteId = :noteId")
    fun getNote(noteId: String): LiveData<SceneryNote>
}