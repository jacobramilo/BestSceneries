package com.jacob.bestsceneries.di.component

import android.app.Application
import com.jacob.bestsceneries.MyApplication
import com.jacob.bestsceneries.di.module.AppModule
import com.jacob.bestsceneries.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MyApplication)
}