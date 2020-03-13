package com.jacob.bestsceneries.di.module

import android.app.Application
import com.jacob.bestsceneries.database.SceneriesDb
import com.jacob.bestsceneries.database.dao.SceneriesDao
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): SceneriesDb {
        return SceneriesDb.getInstance(application.applicationContext)
    }

    @Provides
    @Singleton
    fun provideSceneryDao(database: SceneriesDb): SceneriesDao {
        return database.sceneriesDao()
    }

    @Provides
    fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

}