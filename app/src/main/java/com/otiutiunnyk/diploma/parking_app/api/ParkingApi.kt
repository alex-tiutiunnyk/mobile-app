package com.otiutiunnyk.diploma.parking_app.api

import com.otiutiunnyk.diploma.parking_app.api.Constants.Companion.BASE_URL
import com.otiutiunnyk.diploma.parking_app.api.Constants.Companion.USERS_ENDPOINT
import com.otiutiunnyk.diploma.parking_app.api.models.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ParkingApi {
//    @GET(PARKING_ENDPOINT)
//    suspend fun getParkings(): List<Parking>

    @GET(USERS_ENDPOINT)
    suspend fun getUsers(): List<User>

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

//   @POST(REQUEST_NEW_PARKING_ENDPOINT)
//   suspend fun saveNewParkingRequest(): Response<List<User>>

//   @GET(FAV_PLACES_ENDPOINT)
//   suspend fun getFavoritePlaces(): Response<List<User>>

}