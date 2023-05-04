package com.otiutiunnyk.diploma.parking_app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.otiutiunnyk.diploma.parking_app.DrawerMenuData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerMenu(scaffoldState: ScaffoldState, scope: CoroutineScope, navController: NavController) {
    val menuList = listOf(
        DrawerMenuData.Divider,
        DrawerMenuData.AccountSettings,
        DrawerMenuData.GeneralSettings,
        DrawerMenuData.History,
        DrawerMenuData.InviteFriend,
        DrawerMenuData.ReportIssue
    )

    val bottomMenuList = listOf(
        DrawerMenuData.About,
        DrawerMenuData.SignOut
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Column(
        modifier = Modifier
//            .fillMaxWidth()
            .padding(top = 30.dp, start = 20.dp, bottom = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) { //user circle
            Card(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                backgroundColor = Color.LightGray
            ) {
                Text(
                    text = "A",
                    textAlign = TextAlign.Center, modifier = Modifier.padding(22.dp),
                    fontWeight = FontWeight.Bold, fontSize = 25.sp
                ) //userName[0].toString()
            }

            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }) {
                Icon(
                    Icons.Outlined.ArrowBack, ""
                )
            }
        }
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Aleksandra Tiutiunnyk",
            fontSize = 18.sp
        ) //userName.toString()

        menuList.forEach { item ->
            when {
                item.isDivider -> {
                    Divider(modifier = Modifier.padding(top = 20.dp, bottom = 16.dp))
                }
                else -> {
                    DrawerItem(
                        item,
                        selected = currentRoute == item.route,
                        onItemClick = { onItemClick(item, navController, scope, scaffoldState) })
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        bottomMenuList.forEach { item ->
            DrawerItem(
                item,
                selected = currentRoute == item.route,
                onItemClick = { onItemClick(item, navController, scope, scaffoldState) })
        }
    }
}

@Composable
fun DrawerItem(item: DrawerMenuData, selected: Boolean, onItemClick: (DrawerMenuData) -> Unit) {
    val background = if (selected) Color.LightGray else Color.Transparent
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(top = 16.dp)
            .background(background)
            .clickable { onItemClick(item) }
    ) {
        Image(
            imageVector = item.icon!!,
            contentDescription = item.title!!,
        )
        Text(text = item.title, modifier = Modifier.weight(2.0f).padding(start = 10.dp))
    }
}

fun drawerClose(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    scope.launch {
        scaffoldState.drawerState.close()
    }
}

fun onItemClick(
    item: DrawerMenuData,
    navController: NavController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    navController.navigate(item.route!!) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
    drawerClose(scope, scaffoldState)
}