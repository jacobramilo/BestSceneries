package com.jacob.bestsceneries.di.component

import android.app.Application
import com.jacob.bestsceneries.MapsActivity
import com.jacob.bestsceneries.MyApplication
import com.jacob.bestsceneries.SceneryDetailsActivity
import com.jacob.bestsceneries.di.module.AppModule
import com.jacob.bestsceneries.di.module.NetworkModule
import com.jacob.bestsceneries.di.module.ViewModelModule
import com.jacob.bestsceneries.fragment.SceneryListFragment
import com.jacob.bestsceneries.fragment.SceneryMapFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MyApplication)
    fun inject(mapsActivity: MapsActivity)
    fun inject(sceneryDetailsActivity: SceneryDetailsActivity)

    fun inject(sceneryListFragment: SceneryListFragment)
    fun inject(sceneryMapFragment: SceneryMapFragment)
}