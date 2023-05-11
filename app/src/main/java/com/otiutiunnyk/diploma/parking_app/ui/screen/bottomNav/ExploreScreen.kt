@file:OptIn(ExperimentalMaterialApi::class)

package com.otiutiunnyk.diploma.parking_app.ui.screen.bottomNav

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory.*
import com.google.maps.android.compose.*
import com.otiutiunnyk.diploma.parking_app.MarkersData
import com.otiutiunnyk.diploma.parking_app.R
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

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            BottomSheetContent(
                selectedMarker.value,
                navController,
                coroutineScope,
                bottomSheetScaffoldState
            )
        },
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        modifier = Modifier.padding(bottom = 50.dp)
    ) {
        GoogleMapWithBottomSheet(selectedMarker, bottomSheetScaffoldState, coroutineScope)
    }
}

//
//navController.navigate(item.route) {
//    navController.graph.startDestinationRoute?.let { route ->
//        popUpTo(route) {
//            saveState = true
//        }
//    }
//    launchSingleTop = true
//    restoreState = true
//}
//

@Composable
fun BottomSheetContent(
    marker: Marker?,
    navController: NavController,
    scope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Selected Parking: ${marker?.title}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("marker1")//change to do the routing to item id
            scope.launch {
                bottomSheetScaffoldState.bottomSheetState.collapse()
            }
        }) {
            Text(text = "Do Something")
        }
    }
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

    AndroidView(
        factory = {
            mapView.apply {
                getMapAsync { map ->
                    val cameraPosition = CameraPosition.Builder()
                        .target(kyiv)
                        .zoom(12f)
                        .build()
                    map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

                    //map properties
                    map.mapType = com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL
                    map.isMyLocationEnabled = true
                    map.isTrafficEnabled = true
                    map.isBuildingsEnabled = true

                    //map settings
                    val uiSettings = map.uiSettings
                    uiSettings.isMyLocationButtonEnabled = true
                    uiSettings.isCompassEnabled = true

                    markerMockList.forEach {
                        val markerOptions = MarkerOptions()
                            .position(it.position)
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


//            ) {
//                Card(
//                    modifier = Modifier.background(Color.White, shape = RoundedCornerShape(8.dp))
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .padding(8.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        Text(text = "${item.occupied}/${item.capacity}")
//                        IconButton(onClick = {
//                            onButtonClick(
//                                item,
//                                navController
//                            )
//                        }) {//go to info page about this parking with comments
//                            Icon(imageVector = Icons.Default.MoreHoriz, contentDescription = "")
//                        }
//                    }
//                }

fun onSnippetClick(
    item: MarkersData,
    navController: NavController
) {
    navController.navigate(item.route) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}


@Composable
fun DetailedParkingPage(item: MarkersData) {
    val scrollState = rememberScrollState()
    val comments = mutableMapOf<String, MutableList<String>>(
        "user1" to mutableListOf("Lorem Ipsum", "Fal jeos"),
        "user2" to mutableListOf("Ipsum Data")
    )
    var inputValue by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp)
            .scrollable(
                orientation = Orientation.Vertical,
                state = scrollState
            )
    ) {
        Row() {
            Text(text = "${item.isForDisabledPerson}")
            Text(text = "${item.isForElectricCar}")
        }
        Row() {
            Text(text = "${item.pricePerHour}")
        }
        Text(text = "Comments", fontWeight = FontWeight.Bold)


        LazyColumn(Modifier.weight(1f)) {
            items(comments.keys.toList()) { login ->
                comments[login]!!.forEach { message ->
                    CommentItem(login = login, message = message)
                    Divider()
                }
            }
        }


        Spacer(modifier = Modifier.weight(1f))
        TextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            label = { Text("Add comment") },
            maxLines = 2,
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Send
            ),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    onSend(inputValue = inputValue, comments)
                    inputValue = ""
                }) {
                    Icon(
                        Icons.Filled.Send,
                        contentDescription = "Send",
                        tint = if (inputValue.isNotBlank()) colorResource(id = R.color.purple_500) else Color.Gray
                    )
                }
            }
        )
    }
}

@Composable
fun CommentItem(login: String, message: String) {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape),
            backgroundColor = Color.LightGray
        ) {
            Text(
                text = login[0].toString(),
                textAlign = TextAlign.Center, modifier = Modifier.padding(12.dp),
                fontWeight = FontWeight.Bold, fontSize = 20.sp
            ) //TODO: userName[0].toString()
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = login, fontWeight = FontWeight.Bold)
            Text(text = message)
        }
    }
}

//fun updateComments(
//    comments: MutableMap<String, MutableList<String>>,
//    inputValue: String,
//    login: String = "user2"
//) {
//    if (comments.containsKey(login)) {
//        comments[login]?.add(inputValue)
//    } else {
//        comments[login] = mutableListOf(inputValue)
//    }
//}

fun updateComments(
    comments: MutableMap<String, MutableList<String>>,
    commentsList: MutableList<Pair<String, String>>,
    inputValue: String,
    login: String = "user3"
) {
    if (comments.containsKey(login)) {
        comments[login]?.add(inputValue)
    } else {
        comments[login] = mutableListOf(inputValue)
    }
    commentsList.add(Pair(login, inputValue))
}

fun onSend(
    inputValue: String,
    comments: MutableMap<String, MutableList<String>>,
    login: String = "user2"
) {
    if (inputValue.isNotBlank()) {
        if (comments.containsKey(login)) {
            comments[login]?.add(inputValue)
        } else {
            comments[login] = mutableListOf(inputValue)
        }
    }
}