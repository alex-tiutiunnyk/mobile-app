package com.otiutiunnyk.diploma.parking_app.ui.screen.bottomNav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.otiutiunnyk.diploma.parking_app.MarkersData


@Composable
fun ExploreScreen() {
    MyGoogleMaps()
}

@Composable
fun MyGoogleMaps() {
    val properties by remember {
        mutableStateOf(
            MapProperties(
                isMyLocationEnabled = true,
                isTrafficEnabled = true,
                isBuildingEnabled = true,
                mapType = MapType.NORMAL
            )
        )
    }
    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                myLocationButtonEnabled = true,
                zoomControlsEnabled = false
            )
        )
    }
    val kyiv = LatLng(50.44, 30.52)
    val markerMockList = listOf(
        MarkersData.Marker1,
        MarkersData.Marker2
    )
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(kyiv, 12f)
        }
    ) {
        markerMockList.forEach { item ->

            Marker(
                state = rememberMarkerState(position = item.position),
                title = item.title,
                snippet = item.snippet ?: "",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
            )
        }
    }
}