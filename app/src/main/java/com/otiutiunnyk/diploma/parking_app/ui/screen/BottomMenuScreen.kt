package com.otiutiunnyk.diploma.parking_app.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuScreen(val route: String, val icon: ImageVector, val title: String) {

    object AddNewPlace: BottomMenuScreen("add new place", icon = Icons.Outlined.Add, "Add")
    object FavouritePlaces: BottomMenuScreen("favourite places", icon = Icons.Outlined.Star, "Favourites")
    object Explore: BottomMenuScreen("explore", icon = Icons.Outlined.Place, "Explore")
    object Menu: BottomMenuScreen("configure menu", icon = Icons.Outlined.Menu, "Menu")
}