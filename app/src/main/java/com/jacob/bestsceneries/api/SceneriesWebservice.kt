package com.jacob.bestsceneries.api

import com.jacob.bestsceneries.model.Sceneries
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface SceneriesWebservice {

    @Headers("Accept: application/json")
    @GET("test-locations")
    fun getScenery(): Call<Sceneries>

}