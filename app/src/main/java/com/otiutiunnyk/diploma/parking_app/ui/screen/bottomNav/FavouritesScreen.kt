package com.otiutiunnyk.diploma.parking_app.ui.screen.bottomNav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.otiutiunnyk.diploma.parking_app.api.models.User

@Composable
fun FavouritesScreen(userList: List<User>) {
    println("Inside favourite $userList")

    Box(modifier = Modifier.padding(10.dp)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(userList) { userData ->
                UserItem(userData)
                Spacer(modifier = Modifier.background(Color.Gray))
            }
        }
    }
}

@Composable
fun UserItem(userData: User) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(text = userData.login)
        Text(text = userData.password)
        userData.email?.let { Text(text = it) }
        Text(text = userData.systemLanguage)
        Text(text = userData.birthDate.toString())

        //add three dot icon
    }
}
