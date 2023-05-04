package com.otiutiunnyk.diploma.parking_app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuData(val route: String, val icon: ImageVector, val title: String) {

    object AddNewPlace: BottomMenuData("add new place", icon = Icons.Outlined.AddLocation, "Add") //Add or AddLocation
    object FavouritePlaces: BottomMenuData("favourite places", icon = Icons.Outlined.FavoriteBorder, "Favourites")
    object Explore: BottomMenuData("explore", icon = Icons.Outlined.Place, "Explore")
    object Menu: BottomMenuData("configure menu", icon = Icons.Outlined.Menu, "Menu")
}