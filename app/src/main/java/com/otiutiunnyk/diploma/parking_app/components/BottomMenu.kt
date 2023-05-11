@file:OptIn(ExperimentalMaterialApi::class)

package com.otiutiunnyk.diploma.parking_app.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.otiutiunnyk.diploma.parking_app.BottomMenuData
import com.otiutiunnyk.diploma.parking_app.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomMenu(navController: NavController, scaffoldState: ScaffoldState, scope: CoroutineScope, bottomSheetScaffoldState: BottomSheetScaffoldState) {

    val menuItems = listOf(
        BottomMenuData.AddNewPlace,
        BottomMenuData.FavouritePlaces,
        BottomMenuData.Explore,
        BottomMenuData.Menu
    )

    BottomNavigation(contentColor = colorResource(id = R.color.white))
    {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        menuItems.forEach {
            BottomNavigationItem(
                label = { Text(text = it.title) },
                alwaysShowLabel = true,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.7f),
                selected = currentRoute == it.route,
                onClick = {
                    if (it.route == BottomMenuData.Menu.route)
                        drawerOpen(scope, scaffoldState)
                    else onNavigationItemClick(it, navController, scope, bottomSheetScaffoldState)
                },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title
                    )
                }
            )
        }
    }
}

fun drawerOpen(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    scope.launch {
        scaffoldState.drawerState.open()
    }
}

fun onNavigationItemClick(
    it: BottomMenuData,
    navController: NavController,
    scope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {
    navController.navigate(it.route) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                saveState = true
            }
        }
        scope.launch {
            bottomSheetScaffoldState.bottomSheetState.collapse()
        }
        launchSingleTop = true
        restoreState = true

    }
}