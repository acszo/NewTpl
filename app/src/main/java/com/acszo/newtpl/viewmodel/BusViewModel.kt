package com.acszo.newtpl.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acszo.newtpl.model.Bus
import com.acszo.newtpl.repository.BusRepository
import kotlinx.coroutines.launch

class BusViewModel: ViewModel() {

    private val _buses: MutableLiveData<List<Bus>> = MutableLiveData()
    val buses: LiveData<List<Bus>> get() = _buses

    fun getBuses(stopCode: String) = viewModelScope.launch {
        try {
            val buses: ArrayList<Bus> = filterBuses(stopCode) as ArrayList<Bus>
            if (stopCode == "UD504") {
                buses.addAll(filterBuses("70C64"))
            }
            _buses.postValue(buses)
        } catch(e: Exception) {
            print(e.message)
        }
    }

    private suspend fun filterBuses(stopCode: String): List<Bus> {
        return BusRepository().getBuses(stopCode).filter {
            it.LineCode == "10" || it.LineCode == "S" || it.LineCode == "9"
        }
    }

}