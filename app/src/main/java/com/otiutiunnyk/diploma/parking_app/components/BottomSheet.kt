@file:OptIn(ExperimentalMaterialApi::class)

package com.otiutiunnyk.diploma.parking_app.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.Marker
import com.otiutiunnyk.diploma.parking_app.ui.screen.bottomNav.GoogleMapWithBottomSheet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomSheet(
    navController: NavController,
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    selectedMarker: MutableState<Marker?>
) {
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            BottomSheetContent(
                marker = selectedMarker.value,
                navController = navController,
                scope = coroutineScope,
                bottomSheetScaffoldState = bottomSheetScaffoldState
            )
        },
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        modifier = Modifier.padding(bottom = 50.dp)
    ) {
        GoogleMapWithBottomSheet(selectedMarker, bottomSheetScaffoldState, coroutineScope)
    }
}

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
            .padding(top = 16.dp, end = 16.dp, start = 16.dp, bottom = 20.dp),
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(textAlign = TextAlign.Center, text = "${marker?.title}  ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(textAlign = TextAlign.Center, text = "loaded", fontSize = 16.sp)
            }

            IconButton(
                onClick = { /*favorite state change for the parking*/ }) { // so for parking area I will have different model that will contain ParkingArea model, Comments and Favorites information
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (true) Icons.Outlined.FavoriteBorder else Icons.Outlined.Favorite,
                        contentDescription = ""
                    ) //change to checking state of the parking regarding state
                    Text(text = "  Save")
                }
            }
        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                navController.navigate("marker1")//change to do the routing to item id
                scope.launch {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }) {
                Text(text = "Details")
            }
        }
    }
}