package com.jacob.bestsceneries.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import java.io.Serializable
import java.util.*

@Entity(primaryKeys = ["lat", "lng"])
class Scenery {

    var id = UUID.randomUUID().toString()

    var name: String? = null

    var lat: Double = 0.0

    var lng: Double = 0.0

    var notes: String? = null

    override fun toString(): String {
        return name ?: ""
    }
}