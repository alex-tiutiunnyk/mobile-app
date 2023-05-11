package com.otiutiunnyk.diploma.parking_app.api

class Constants {
    companion object{
//        const val BASE_URL = "http://192.168.0.203:8080/"
        const val BASE_URL = "http://192.168.1.103:8080/"
        const val PARKING_ENDPOINT = "parkings"
        const val REQUEST_NEW_PARKING_ENDPOINT = "$PARKING_ENDPOINT/newrequest"
        const val USERS_ENDPOINT = "users"
        const val FAV_PLACES_ENDPOINT = "favplaces"
    }
}