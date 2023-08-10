package com.acszo.newtpl.repository

import com.acszo.newtpl.model.Bus
import com.acszo.newtpl.service.RetrofitInstance

class  BusRepository {

    suspend fun getBuses(stopCode: String): ArrayList<Bus> = RetrofitInstance.api.getBuses(stopCode, true)

}