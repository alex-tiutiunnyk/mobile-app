package com.otiutiunnyk.diploma.parking_app

import com.google.android.gms.maps.model.LatLng

sealed class MarkersData(val position: LatLng, val title: String, val snippet: String? = null) {
    object Marker1 : MarkersData(LatLng(50.443451, 30.517158), "3/10")
    object Marker2 : MarkersData(LatLng(50.4263, 30.496578), "9/10")
}

