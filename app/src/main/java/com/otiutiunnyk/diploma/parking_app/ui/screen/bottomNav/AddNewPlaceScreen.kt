package com.otiutiunnyk.diploma.parking_app.ui.screen.bottomNav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.otiutiunnyk.diploma.parking_app.R
import com.otiutiunnyk.diploma.parking_app.components.NewParkingRequestDialog

@Composable
fun AddNewPlaceScreen(openNewParkingDialog: MutableState<Boolean>) {
    NewGoogleMaps(openNewParkingDialog)
}

@Composable
fun NewGoogleMaps(openNewParkingDialog: MutableState<Boolean>) {
//    val newParkingRequestViewModel: NewParkingRequestViewModel = viewModel()

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
    val cameraPositionState =
        rememberCameraPositionState { position = CameraPosition.fromLatLngZoom(kyiv, 12f) }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = cameraPositionState
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 45.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.map_icon_original),
            modifier = Modifier.size(45.dp),
            contentDescription = ""
        )
    }
//    Text(
//        text = "Is camera moving: ${cameraPositionState.isMoving}" + "\n Latitude and Longitude: ${cameraPositionState.position.target.latitude} and ${cameraPositionState.position.target.longitude}",
//        textAlign = TextAlign.Center
//    )

    if (openNewParkingDialog.value) {
        NewParkingRequestDialog(
            openNewParkingDialog = openNewParkingDialog,
            LatLng(
                cameraPositionState.position.target.latitude,
                cameraPositionState.position.target.longitude
            )
        )
    }
}

//fun callApiCreateNewParkingRequest(newParkingRequestViewModel: NewParkingRequestViewModel, newParkingRequest: NewParkingRequest) {
//    val newParking = newParkingRequestViewModel.newParkingRequestResponse
//    newParkingRequestViewModel.createNewParkingRequest(newParkingRequest)
//}
