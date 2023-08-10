package com.acszo.newtpl.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acszo.newtpl.model.Bus
import com.acszo.newtpl.repository.BusRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BusViewModel: ViewModel() {

    private val _buses: MutableStateFlow<List<Bus>> = MutableStateFlow(emptyList())
    val buses = _buses.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun getBuses(stopCode: String) = viewModelScope.launch {
        _isLoading.update { true }
        try {
            val buses = BusRepository().getBuses(stopCode)
            if (stopCode == "UD504") {
                buses.addAll(BusRepository().getBuses("70C64"))
            }
            _buses.update { filterBuses(buses) }
            _isLoading.update { false }
        } catch(e: Exception) {
            print(e.message)
        }
    }

    private fun filterBuses(buses: ArrayList<Bus>): List<Bus> {
        return buses.filter {
            it.lineCode == "9" || it.lineCode == "10" || it.lineCode == "S"
        }.distinctBy { it.race }.sortedBy { it.time }
    }

}