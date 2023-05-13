@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)

package com.otiutiunnyk.diploma.parking_app.ui.screen.bottomNav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory.*
import com.google.maps.android.compose.*
import com.otiutiunnyk.diploma.parking_app.MarkersData
import com.otiutiunnyk.diploma.parking_app.components.BottomSheet
import com.otiutiunnyk.diploma.parking_app.rememberMapViewWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun ExploreScreen(
    navController: NavController,
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {
    val selectedMarker = remember { mutableStateOf<Marker?>(null) }

    BottomSheet(navController, coroutineScope, bottomSheetScaffoldState, selectedMarker)
}

@SuppressLint("MissingPermission")
@Composable
fun GoogleMapWithBottomSheet(
    selectedMarker: MutableState<Marker?>,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    scope: CoroutineScope
) {
    val mapView = rememberMapViewWithLifecycle()
    val kyiv = LatLng(50.44, 30.52)
    val markerMockList = listOf(
        MarkersData.Marker1,
        MarkersData.Marker2,
        MarkersData.Marker3
    )

    val polygonOptions = listOf(
        PolygonOptions()
            .add(LatLng(50.450565, 30.520874))
            .add(LatLng(50.449933, 30.520616))
            .add(LatLng(50.449892, 30.521277))
            .add(LatLng(50.450524, 30.521553))
            .strokeWidth(0.5f)
            .fillColor(0x7F00FF00),
        PolygonOptions()
            .add(LatLng(50.450789, 30.518947))
            .add(LatLng(50.450787, 30.519986))
            .add(LatLng(50.450195, 30.520004))
            .add(LatLng(50.450200, 30.518962))
            .strokeWidth(0.5f)
            .fillColor(0x7F0000FF)
    )

    AndroidView(
        factory = {
            mapView.apply {
                getMapAsync { map ->
                    val cameraPosition = CameraPosition.Builder()
                        .target(kyiv) //make on top (upper)
                        .zoom(18f)
                        .build()
                    map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

                    //map properties
                    map.mapType = com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL
                    map.isMyLocationEnabled = true
                    map.isTrafficEnabled = true
                    map.isBuildingsEnabled = true


                    val centerLatLngList = mutableListOf<LatLng>()
                    polygonOptions.forEach { polygonOption ->
                        map.addPolygon(polygonOption)
                        centerLatLngList.add(getPolygonCenterPoint(polygonOption.points))
                    }


                    //map settings
                    val uiSettings = map.uiSettings
                    uiSettings.isMyLocationButtonEnabled = true
                    uiSettings.isCompassEnabled = true

                    markerMockList.forEach {
                        val markerOptions = MarkerOptions()
                            .position(centerLatLngList[1])
                            .title("${it.occupied}/${it.capacity}")
                            .icon(
                                if (it.occupied == it.capacity) defaultMarker(HUE_RED)
                                else defaultMarker(HUE_GREEN)
                            )
                        map.addMarker(markerOptions)
                    }

                    // Set the selected marker when the marker is clicked
                    map.setOnMarkerClickListener { clickedMarker ->
                        val values: List<String> =
                            selectedMarker.value?.let { it.title?.split("/") } ?: emptyList()
                        if (values.isNotEmpty()) {
                            selectedMarker.value?.setIcon(
                                if (values[0] == values[1]) defaultMarker(HUE_RED) else defaultMarker(
                                    HUE_GREEN
                                )
                            )
                        }
                        clickedMarker.setIcon(
                            defaultMarker(HUE_ORANGE)
                        )
                        selectedMarker.value = clickedMarker
                        scope.launch {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        }
                        true
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

fun getPolygonCenterPoint(points: List<LatLng>): LatLng {
    var latSum = 0.0
    var lngSum = 0.0
    for (point in points) {
        latSum += point.latitude
        lngSum += point.longitude
    }
    val latCenter = latSum / points.size
    val lngCenter = lngSum / points.size
    return LatLng(latCenter, lngCenter)
}