package com.otiutiunnyk.diploma.parking_app.api.models.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otiutiunnyk.diploma.parking_app.api.ParkingApi
import com.otiutiunnyk.diploma.parking_app.api.models.Parking
import kotlinx.coroutines.launch

class ParkingViewModel : ViewModel() {
    var parkingsListResponse: List<Parking> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    val parkingApi = ParkingApi.getInstance()
    fun createNewParkingRequest() {
        viewModelScope.launch {
            try {
                val response = parkingApi.getParkings()
                println("Testing print $response")
                parkingsListResponse = response
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                println("Error Testing $errorMessage")
            }
        }
    }
}