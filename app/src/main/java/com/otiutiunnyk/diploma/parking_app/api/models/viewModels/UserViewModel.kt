package com.otiutiunnyk.diploma.parking_app.api.models.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otiutiunnyk.diploma.parking_app.api.ParkingApi
import com.otiutiunnyk.diploma.parking_app.api.models.User
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    var usersListResponse: List<User> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun getUserList() {
        viewModelScope.launch {
            val parkingApi = ParkingApi.getInstance()
            try {
                val userList = parkingApi.getUsers()
                println("Testing print $userList")
                usersListResponse = userList
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                println("Error Testing $errorMessage")
            }
        }
    }
}