package com.jacob.bestsceneries.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SceneryNote {

    @PrimaryKey
    var noteId: Int? = null

    var note: String? = null

}