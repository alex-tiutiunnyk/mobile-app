package com.otiutiunnyk.diploma.parking_app.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.ktx.awaitMap
import com.otiutiunnyk.diploma.parking_app.BottomMenuData
import com.otiutiunnyk.diploma.parking_app.DrawerMenuData
import com.otiutiunnyk.diploma.parking_app.components.BottomMenu
import com.otiutiunnyk.diploma.parking_app.components.DrawerMenu
import com.otiutiunnyk.diploma.parking_app.components.LocationFab
import com.otiutiunnyk.diploma.parking_app.rememberMapViewWithLifecycle
import com.otiutiunnyk.diploma.parking_app.ui.screen.*
import com.otiutiunnyk.diploma.parking_app.ui.screen.drawer.AboutScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ParkingApp() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    val mapView = rememberMapViewWithLifecycle()

    MainScreen(navController = navController, scrollState = scrollState, mapView = mapView)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState, mapView: MapView) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerMenu(
                scaffoldState = scaffoldState,
                scope = coroutineScope,
                navController = navController
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen, //allows to close gestures only if the drawerMenu is open
//        drawerGesturesEnabled = false, //discards the unnecessary gestures to open the drawerMenu
        bottomBar = {
            BottomMenu(
                navController = navController,
                scaffoldState = scaffoldState,
                scope = coroutineScope
            )
        },
        floatingActionButton = { LocationFab() })
    {
        Navigation(navController = navController)
        MyMapContent(mapView = mapView)
    }
}

@Composable
fun MyMapContent(mapView: MapView) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {
        AndroidView({ mapView }) { mapView ->
            CoroutineScope(Dispatchers.Main).launch {
                val map = mapView.awaitMap()
                map.uiSettings.isZoomControlsEnabled = true

                val pickUp = LatLng(-35.016, 143.321)
                val destination = LatLng(-32.491, 147.309)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 6f))
                val markerOptions = MarkerOptions()
                    .title("Sydney Opera House")
                    .position(pickUp)
                map.addMarker(markerOptions)

                val markerOptionsDestination = MarkerOptions()
                    .title("Restaurant Hubert")
                    .position(destination)
                map.addMarker(markerOptionsDestination)

                map.addPolyline(
                    PolylineOptions().add(
                        pickUp,
                        LatLng(-34.747, 145.592),
                        LatLng(-34.364, 147.891),
                        LatLng(-33.501, 150.217),
                        LatLng(-32.306, 149.248),
                        destination
                    )
                )

            }


        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomMenuData.Explore.route) {
        composable(BottomMenuData.Explore.route) {
            ExploreScreen()
//            arguments = listOf(
//                navArgument("newsId") { type = NavType.IntType }
//            )) { navBackStackEntry ->
//            val id = navBackStackEntry.arguments?.getInt("newsId")
//            val newsData = MockData.getNews(id)
//            DetailScreen(newsData, scrollState, navController)
        }
        bottomNavigation()
        drawerNavigation()
    }
}

fun NavGraphBuilder.bottomNavigation() {
    composable(BottomMenuData.AddNewPlace.route) {
        AddNewPlaceScreen()
    }
    composable(BottomMenuData.FavouritePlaces.route) {
        FavouritesScreen()
    }
    composable(BottomMenuData.Explore.route) {
        ExploreScreen()
    }
    composable(BottomMenuData.Menu.route) {
//        MenuScreen()
    }
}

fun NavGraphBuilder.drawerNavigation() {
    composable(DrawerMenuData.AccountSettings.route!!) {
    }
    composable(DrawerMenuData.GeneralSettings.route!!) {
    }
    composable(DrawerMenuData.History.route!!) {
    }
    composable(DrawerMenuData.InviteFriend.route!!) {
    }
    composable(DrawerMenuData.ReportIssue.route!!) {
    }
    composable(DrawerMenuData.About.route!!) {
        AboutScreen()
    }
    composable(DrawerMenuData.SignOut.route!!) {
    }
}