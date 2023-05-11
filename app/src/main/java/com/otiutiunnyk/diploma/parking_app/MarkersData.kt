package com.otiutiunnyk.diploma.parking_app

import com.google.android.gms.maps.model.LatLng

sealed class MarkersData(val route: String, val position: LatLng, val occupied: Int? = null, val capacity: Int, val pricePerHour: Double? = null, val isForDisabledPerson: Boolean? = null, val isForElectricCar: Boolean? = null) { //ad the list of comments as well
    object Marker1 : MarkersData("marker1", LatLng(50.443451, 30.517158), 3, 10, 10.5, true, false)
    object Marker2 : MarkersData("marker2", LatLng(50.4263, 30.496578), 9, 12)
    object Marker3 : MarkersData("marker3", LatLng(50.4163, 30.48), 5, 5)
}

