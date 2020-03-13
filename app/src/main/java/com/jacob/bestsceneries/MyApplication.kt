package com.jacob.bestsceneries

import android.app.Application
import com.jacob.bestsceneries.di.component.DaggerAppComponent

class MyApplication: Application() {

    val appComponent = DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }
}