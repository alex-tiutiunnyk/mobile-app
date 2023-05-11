package com.otiutiunnyk.diploma.parking_app.api.models

data class NewParkingRequest(
    var geolocation: String,
    var capacity: Int,
    var userId: Long,
    var pricePerHour: Double?,
    var isForDisabledPerson: Boolean?,
    var isForElectricCar: Boolean?,
    var comment: String?
)
