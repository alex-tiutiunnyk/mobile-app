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
fun BottomMenu(navController: NavController, scaffoldState: ScaffoldState, scope: CoroutineScope) {

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
            if (it.route == BottomMenuData.Menu.route) {
                BottomNavigationItem(
                    label = { Text(text = it.title) },
                    alwaysShowLabel = true,
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.White.copy(0.7f),
                    selected = currentRoute == it.route,
                    onClick = {
                        //scaffold changes
                        scope.launch {
                            scaffoldState.drawerState.open() //add the focus stop and maybe change the side
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = it.title
                        )
                    }
                )
            } else {
                BottomNavigationItem(
                    label = { Text(text = it.title) },
                    alwaysShowLabel = true,
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.White.copy(0.7f),
                    selected = currentRoute == it.route,
                    onClick = {
                        navController.navigate(it.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
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
}


//
//@Composable
//fun BottomNavigationItem(currentRoute:String?, navController: NavController, it:BottomMenuScreen) {
//        label = { Text(text = it.title) },
//        alwaysShowLabel = true,
//        selectedContentColor = Color.White,
//        unselectedContentColor = Color.Gray,
//        selected = currentRoute == it.route,
//        onClick = {
//            navController.navigate(it.route) {
//                navController.graph.startDestinationRoute?.let { route ->
//                    popUpTo(route) {
//                        saveState = true
//                    }
//                }
//                launchSingleTop = true
//                restoreState = true
//            }
//        },
//        icon = {
//            Icon(
//                imageVector = it.icon,
//                contentDescription = it.title
//            )
//        }
//}