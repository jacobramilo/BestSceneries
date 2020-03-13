package com.jacob.bestsceneries.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jacob.bestsceneries.database.dao.SceneriesDao
import com.jacob.bestsceneries.database.entity.Scenery

@Database(entities = [Scenery::class], version = 1)
abstract class SceneriesDb: RoomDatabase() {

    abstract fun sceneriesDao(): SceneriesDao

    companion object {
            @Volatile private var INSTANCE: SceneriesDb? = null

            fun getInstance(context: Context): SceneriesDb =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

            private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext, SceneriesDb::class.java, "SceneriesDb.db")
                    .build()

    }

}