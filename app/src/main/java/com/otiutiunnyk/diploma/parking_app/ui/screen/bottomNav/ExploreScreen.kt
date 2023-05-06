package com.otiutiunnyk.diploma.parking_app.ui.screen.bottomNav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

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
//    val currentState = null
//    val cameraPositionState = rememberCameraPositionState {
//        position = CameraPosition.fromLatLngZoom(currentState, 10f)
//    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(44.837789, -0.57918), 12f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        onMapLoaded = {},
        properties = properties,
        uiSettings = uiSettings
//        cameraPositionState = cameraPositionState
    ) {
        Marker()
    }
}

