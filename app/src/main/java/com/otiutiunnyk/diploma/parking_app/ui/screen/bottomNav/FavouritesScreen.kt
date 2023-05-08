package com.otiutiunnyk.diploma.parking_app.ui.screen.bottomNav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.otiutiunnyk.diploma.parking_app.api.models.User

@Composable
fun FavouritesScreen(userList: List<User>) {
//    val users = MutableStateFlow(emptyList<User>())
    println("Inside favourite $userList")
    userList.forEach { user ->
        /* LazyColumn(modifier = Modifier
             .fillMaxHeight()
             .background(Color.LightGray)) {
             */
        println("Test555: $user")
        Text(user.password)
        Text("${user.email}")
        Text("${user.birthDate}")
        Text(user.systemLanguage)
        Spacer(modifier = Modifier.background(Color.Blue))
    }
}