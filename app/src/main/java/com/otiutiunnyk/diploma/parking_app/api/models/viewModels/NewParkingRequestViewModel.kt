package com.otiutiunnyk.diploma.parking_app.api.models.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otiutiunnyk.diploma.parking_app.api.ParkingApi
import com.otiutiunnyk.diploma.parking_app.api.models.NewParkingRequest
import kotlinx.coroutines.launch

class NewParkingRequestViewModel : ViewModel() {
    var newParkingRequestResponse: MutableLiveData<NewParkingRequest> = MutableLiveData()
    var errorMessage: String by mutableStateOf("")

    val parkingApi = ParkingApi.getInstance()
    fun createNewParkingRequest(newParkingRequest: NewParkingRequest) {
        viewModelScope.launch {
            try {
                val response = parkingApi.saveNewParkingRequest(newParkingRequest)
                println("Testing print $response")
                newParkingRequestResponse.value = response
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                println("Error Testing $errorMessage")
            }
        }
    }
}