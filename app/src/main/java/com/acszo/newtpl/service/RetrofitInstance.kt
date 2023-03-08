package com.acszo.newtpl.service

import com.acszo.newtpl.service.Api.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: BusService by lazy {
            retrofit.create(BusService::class.java)
        }
    }

}