package com.acszo.newtpl.service

import com.acszo.newtpl.model.Bus
import retrofit2.http.GET
import retrofit2.http.Query

interface BusService {

    @GET("polemonitor/mrcruns")
    suspend fun getBuses(@Query("StopCode") stopCode: String, @Query("IsUrban") isUrban: Boolean): ArrayList<Bus>

}