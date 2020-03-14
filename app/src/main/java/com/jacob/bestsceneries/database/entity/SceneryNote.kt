package com.jacob.bestsceneries.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SceneryNote {

    @PrimaryKey
    var noteId: Long = 0L

    var note: String? = null

}