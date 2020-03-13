package com.jacob.bestsceneries.di.module

import com.jacob.bestsceneries.api.SceneriesWebservice
import com.jacob.bestsceneries.util.Constant
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides @Singleton fun provideWebservice(): SceneriesWebservice {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SceneriesWebservice::class.java)
    }

}