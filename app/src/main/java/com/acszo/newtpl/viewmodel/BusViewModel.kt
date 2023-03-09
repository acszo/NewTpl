package com.acszo.newtpl.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acszo.newtpl.model.Bus
import com.acszo.newtpl.repository.BusRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BusViewModel: ViewModel() {

    private val _buses: MutableLiveData<List<Bus>> = MutableLiveData()
    val buses: LiveData<List<Bus>> get() = _buses

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun getBuses(stopCode: String) = viewModelScope.launch {
        _isLoading.value = true
        try {
            val buses: ArrayList<Bus> = BusRepository().getBuses(stopCode)
            if (stopCode == "UD504") {
                buses.addAll(BusRepository().getBuses("70C64"))
            }
            _buses.postValue(filterBuses(buses))
            _isLoading.value = false
        } catch(e: Exception) {
            print(e.message)
        }
    }

    private fun filterBuses(buses: ArrayList<Bus>): List<Bus> {
        return buses.filter {
            it.LineCode == "9" || it.LineCode == "10" || it.LineCode == "S"
        }.distinctBy { it.Race }
    }

}