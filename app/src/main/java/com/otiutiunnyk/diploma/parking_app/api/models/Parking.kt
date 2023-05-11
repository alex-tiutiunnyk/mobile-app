package com.otiutiunnyk.diploma.parking_app.api.models

data class Parking(
    var geolocation: String,
    var capacity: Int,
    var isForDisabledPerson: Boolean?,
    var isForElectricCar: Boolean?,
    var priceHour: Double?
)
