package com.jacob.bestsceneries.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

@Entity
class Scenery {

    @PrimaryKey
    var id = this.hashCode()

    var name: String? = null

    var lat: Double = 0.0

    var lng: Double = 0.0

    override fun hashCode(): Int {
        val latLng = LatLng(lat, lng)
        return latLng.hashCode()
    }

    override fun toString(): String {
        return name ?: ""
    }
}