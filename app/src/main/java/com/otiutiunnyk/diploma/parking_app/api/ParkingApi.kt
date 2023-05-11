package com.otiutiunnyk.diploma.parking_app.api

import com.otiutiunnyk.diploma.parking_app.api.Constants.Companion.BASE_URL
import com.otiutiunnyk.diploma.parking_app.api.Constants.Companion.PARKING_ENDPOINT
import com.otiutiunnyk.diploma.parking_app.api.Constants.Companion.REQUEST_NEW_PARKING_ENDPOINT
import com.otiutiunnyk.diploma.parking_app.api.Constants.Companion.USERS_ENDPOINT
import com.otiutiunnyk.diploma.parking_app.api.models.NewParkingRequest
import com.otiutiunnyk.diploma.parking_app.api.models.Parking
import com.otiutiunnyk.diploma.parking_app.api.models.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ParkingApi {

    //USERS
    @GET(USERS_ENDPOINT)
    suspend fun getUsers(): List<User>

    @POST(USERS_ENDPOINT)
    suspend fun createUser(@Body requestBody: User): User
    //PARKING AREAS
        @GET(PARKING_ENDPOINT)
        suspend fun getParkings(): List<Parking>

    //PARKING REQUESTS (DELETE, NEW)
    @POST(REQUEST_NEW_PARKING_ENDPOINT)
    suspend fun saveNewParkingRequest(@Body requestBody: NewParkingRequest): NewParkingRequest
//    @GET(FAV_PLACES_ENDPOINT)
//    suspend fun getFavoritePlaces(): Response<List<User>>

    companion object {
        var parkingApi: ParkingApi? = null
        fun getInstance(): ParkingApi {
            if (parkingApi == null) {
                parkingApi = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ParkingApi::class.java)
            }
            return parkingApi!!
        }
    }
}