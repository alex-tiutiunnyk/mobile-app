package com.otiutiunnyk.diploma.parking_app.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.otiutiunnyk.diploma.parking_app.components.BottomMenu
import com.otiutiunnyk.diploma.parking_app.ui.screen.*

@Composable
fun ParkingApp() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()

    MainScreen(navController = navController, scrollState = scrollState)
}

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState) {
    Scaffold(
        topBar = ,
        bottomBar = {
        BottomMenu(navController = navController)
    }) {
        Navigation(navController = navController, scrollState = scrollState)
    }
}

@Composable
fun Navigation(navController: NavHostController, scrollState: ScrollState) {
    NavHost(navController = navController, startDestination = "explore") {
        bottomNavigation()
        composable("explore"){
            ExploreScreen()
//            arguments = listOf(
//                navArgument("newsId") { type = NavType.IntType }
//            )) { navBackStackEntry ->
//            val id = navBackStackEntry.arguments?.getInt("newsId")
//            val newsData = MockData.getNews(id)
//            DetailScreen(newsData, scrollState, navController)
        }
    }
}

fun NavGraphBuilder.bottomNavigation() {
    composable(BottomMenuScreen.AddNewPlace.route) {
       AddNewPlaceScreen()
    }
    composable(BottomMenuScreen.FavouritePlaces.route) {
        FavouritesScreen()
    }
    composable(BottomMenuScreen.Explore.route) {
        ExploreScreen()
    }
    composable(BottomMenuScreen.Menu.route) {
        MenuScreen()
    }
}