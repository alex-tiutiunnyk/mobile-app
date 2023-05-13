package com.otiutiunnyk.diploma.parking_app.ui.screen.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.otiutiunnyk.diploma.parking_app.MarkersData
import com.otiutiunnyk.diploma.parking_app.R

@Composable
fun ParkingAreaInfo(item: MarkersData, navController: NavController) {
    val scrollState = rememberScrollState()
    val comments = mutableMapOf(
        "user1" to mutableListOf("Lorem Ipsum", "Fal jeos"),
        "user2" to mutableListOf("Ipsum Data"),
        "Kevin" to mutableListOf("Data"),
        "Marco" to mutableListOf("TEst sdlk")
    )
    var inputValue by remember {
        mutableStateOf("")
    }
    Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        IconButton(
            onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
        }
        IconButton(
            onClick = { /*favorite state change for the parking*/ }) { // so for parking area I will have different model that will contain ParkingArea model, Comments and Favorites information
            Icon(imageVector = if(true) Icons.Outlined.FavoriteBorder else Icons.Outlined.Favorite, contentDescription = "") //change to checking state of the parking regarding state
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 55.dp, top = 25.dp, start = 16.dp, end = 16.dp)
            .scrollable(
                orientation = Orientation.Vertical,
                state = scrollState
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(SolidColor(Color(android.graphics.Color.parseColor("#005A8C"))))
                    .padding(all = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LocalParking,
                    contentDescription = "",
                    modifier = Modifier.size(55.dp),
                    tint = androidx.compose.ui.graphics.Color.White
                )
            }
        }

        ParkingInfo(item)

//Comments
        Text(
            modifier = Modifier.padding(top = 16.dp, bottom = 10.dp),
            text = "Comments",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        LazyColumn(Modifier.weight(1f)) {
            items(comments.keys.toList()) { login ->
                comments[login]!!.forEach { message ->
                    CommentItem(login = login, message = message)
                    Divider()
                }
            }
        }


//        Spacer(modifier = Modifier.weight(1f))
        TextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            label = { Text("Add comment") },
            maxLines = 2,
            textStyle = TextStyle(color = androidx.compose.ui.graphics.Color.Black, fontSize = 15.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 15.dp),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Send
            ),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    onSend(inputValue = inputValue, comments)
                    inputValue = ""
                }) {
                    Icon(
                        Icons.Filled.Send,
                        contentDescription = "Send",
                        tint = if (inputValue.isNotBlank()) colorResource(id = R.color.purple_500) else androidx.compose.ui.graphics.Color.Gray
                    )
                }
            }
        )
    }
}

@Composable
fun ParkingInfo(item: MarkersData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Loaded", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = "${item.occupied}/${item.capacity}", fontSize = 16.sp)
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Price /hour", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = "${item.pricePerHour}", fontSize = 16.sp)
        }
    }
    Text(text = "Details", fontSize = 18.sp, fontWeight = FontWeight.Bold)
    Row(modifier = Modifier.padding(top = 5.dp, bottom = 2.dp)) {
        Icon(
            modifier = Modifier.padding(end = 8.dp),
            imageVector = if (item.isForDisabledPerson) Icons.Outlined.CheckCircle else Icons.Outlined.Cancel,
            contentDescription = "",
            tint = Color(android.graphics.Color.parseColor("#32CD32"))
        )
        Text(text = "Places for disabled people")
    }
    Row() {
        Icon(
            modifier = Modifier.padding(end = 8.dp),
            imageVector = if (item.isForDisabledPerson) Icons.Outlined.CheckCircle else Icons.Outlined.Cancel,
            contentDescription = "",
            tint = Color(android.graphics.Color.parseColor("#EC0D00"))
        )
        Text(text = "Places for electric cars")
    }
}

@Composable
fun CommentItem(login: String, message: String) {
    Row(
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
    ) {
        Card(
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape),
            backgroundColor = androidx.compose.ui.graphics.Color.LightGray
        ) {
            Text(
                text = login[0].toString(),
                textAlign = TextAlign.Center, modifier = Modifier.padding(12.dp),
                fontWeight = FontWeight.Bold, fontSize = 20.sp
            ) //TODO: userName[0].toString()
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = login, fontWeight = FontWeight.Bold)
            Text(text = message)
        }
    }
}

//fun updateComments(
//    comments: MutableMap<String, MutableList<String>>,
//    inputValue: String,
//    login: String = "user2"
//) {
//    if (comments.containsKey(login)) {
//        comments[login]?.add(inputValue)
//    } else {
//        comments[login] = mutableListOf(inputValue)
//    }
//}

fun updateComments(
    comments: MutableMap<String, MutableList<String>>,
    commentsList: MutableList<Pair<String, String>>,
    inputValue: String,
    login: String = "user3"
) {
    if (comments.containsKey(login)) {
        comments[login]?.add(inputValue)
    } else {
        comments[login] = mutableListOf(inputValue)
    }
    commentsList.add(Pair(login, inputValue))
}

fun onSend(
    inputValue: String,
    comments: MutableMap<String, MutableList<String>>,
    login: String = "user2"
) {
    if (inputValue.isNotBlank()) {
        if (comments.containsKey(login)) {
            comments[login]?.add(inputValue)
        } else {
            comments[login] = mutableListOf(inputValue)
        }
    }
}