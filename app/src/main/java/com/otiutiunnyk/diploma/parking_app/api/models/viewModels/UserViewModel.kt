package com.otiutiunnyk.diploma.parking_app.api.models.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otiutiunnyk.diploma.parking_app.api.ParkingApi
import com.otiutiunnyk.diploma.parking_app.api.models.User
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    var usersListResponse:  List<User> by mutableStateOf(listOf())
    var newUserResponse: MutableLiveData<User> = MutableLiveData()
    var errorMessage: String by mutableStateOf("")

    val parkingApi = ParkingApi.getInstance()
    fun getUserList() {
        viewModelScope.launch {
            try {
                val response = parkingApi.getUsers()
                usersListResponse = response
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun createNewUser(user: User) {
        viewModelScope.launch {
            try {
                val response = parkingApi.createUser(user)
                newUserResponse.value = response
                println("ertsdlgkTETE $newUserResponse")
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                println("Error Testing $errorMessage")
            }
        }
    }
}