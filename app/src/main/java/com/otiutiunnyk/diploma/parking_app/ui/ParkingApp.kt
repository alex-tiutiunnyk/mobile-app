package com.otiutiunnyk.diploma.parking_app.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.otiutiunnyk.diploma.parking_app.BottomMenuData
import com.otiutiunnyk.diploma.parking_app.DrawerMenuData
import com.otiutiunnyk.diploma.parking_app.components.BottomMenu
import com.otiutiunnyk.diploma.parking_app.components.DrawerMenu
import com.otiutiunnyk.diploma.parking_app.components.ParkingDialog
import com.otiutiunnyk.diploma.parking_app.components.SubmitFab
import com.otiutiunnyk.diploma.parking_app.ui.screen.*
import com.otiutiunnyk.diploma.parking_app.ui.screen.bottomNav.AddNewPlaceScreen
import com.otiutiunnyk.diploma.parking_app.ui.screen.bottomNav.ExploreScreen
import com.otiutiunnyk.diploma.parking_app.ui.screen.bottomNav.FavouritesScreen
import com.otiutiunnyk.diploma.parking_app.ui.screen.drawer.AboutScreen

@Composable
fun ParkingApp() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    MainScreen(
        navController = navController,
        scrollState = scrollState,
        currentRoute = currentRoute
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController,
    scrollState: ScrollState,
    currentRoute: String?
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val openNewParkingDialog = remember { mutableStateOf(false) }
    val openAccelerometerDialog = remember { mutableStateOf(true) }
    val freePlacesNumber = remember { mutableStateOf(0) } //to pass the value to the server
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
        floatingActionButton = {
            if (currentRoute == BottomMenuData.AddNewPlace.route) SubmitFab(openNewParkingDialog)
        })
    {
//        if (openAccelerometerDialog.value) {
//            ParkingDialog(openAccelerometerDialog)
//        }
        Navigation(navController = navController, openNewParkingDialog)
    }
}

@Composable
fun Navigation(navController: NavHostController, openNewParkingDialog:MutableState<Boolean>) {
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
        bottomNavigation(openNewParkingDialog)
        drawerNavigation()
    }
}

fun NavGraphBuilder.bottomNavigation(openNewParkingDialog: MutableState<Boolean>) {
    composable(BottomMenuData.AddNewPlace.route) {
        AddNewPlaceScreen(openNewParkingDialog)
    }
    composable(BottomMenuData.FavouritePlaces.route) {
        FavouritesScreen()
    }
    composable(BottomMenuData.Explore.route) {
        ExploreScreen()
    }
    composable(BottomMenuData.Menu.route) {
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