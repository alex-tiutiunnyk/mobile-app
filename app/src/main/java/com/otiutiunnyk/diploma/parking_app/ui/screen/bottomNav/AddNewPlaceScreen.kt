package com.otiutiunnyk.diploma.parking_app.ui.screen.bottomNav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun AddNewPlaceScreen() {
    NewGoogleMaps()
}

@Composable
fun NewGoogleMaps() {
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

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(kyiv, 12f)
        }
    ) {
        Marker(
            state = rememberMarkerState(position = kyiv), //change to the center always (see the video)
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
        )
    }
}
