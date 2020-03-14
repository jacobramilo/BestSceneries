package com.jacob.bestsceneries.database.entity

import androidx.room.Entity
import java.util.*

@Entity(primaryKeys = ["lat", "lng"])
class Scenery {

    var noteId: String = UUID.randomUUID().toString()

    var name: String? = null

    var lat: Double = 0.0

    var lng: Double = 0.0

    override fun toString(): String {
        return name ?: ""
    }
}