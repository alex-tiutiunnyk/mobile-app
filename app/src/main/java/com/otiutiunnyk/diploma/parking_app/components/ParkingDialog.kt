package com.otiutiunnyk.diploma.parking_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

//jump when the vehicle is stopped and person took his phone when going out the car
@Composable
fun ParkingDialog(openDialog: MutableState<Boolean>) {
    Dialog(
        onDismissRequest = { openDialog.value = false },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        ParkingDialogUI(openDialog)
    }
}

@Composable
fun ParkingDialogUI(openDialog: MutableState<Boolean>) {
    var capacity: Int? by remember { mutableStateOf(0) }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(all = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
           //first row
             Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Looks like you're parked",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            //second row
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Please tell how many free places so you see near you to help others")
            }

            //numbers row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(
                    onClick = { capacity = capacity?.minus(1) },
                    enabled = capacity != 0,
                    shape = CircleShape

                ) {
                    Icon(imageVector = Icons.Outlined.Remove, contentDescription = "")
                }
                TextField(
                    modifier = Modifier
                        .height(50.dp)
                        .width(70.dp)
                        .padding(start = 10.dp, end = 10.dp),
                    value = capacity.toString(),
                    onValueChange = {
                        if (it.isEmpty()) {
                            capacity = 0
                        } else {
                            val i = it.toIntOrNull()
                            if (i != null) {
                                capacity = i
                            }
                        }
                    }
                )
                OutlinedButton(
                    onClick = { capacity = capacity?.plus(1) },
                    enabled = true, //change to fullCapacity of the parking check
                    shape = CircleShape
                ) {
                    Icon(imageVector = Icons.Outlined.Add, contentDescription = "")
                }
            }

            //submit row
            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(
                    onClick = { openDialog.value = false },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    shape = CircleShape
                ) {
                    Text(text = "Cancel", color = MaterialTheme.colors.primary)
                }
                Button(
                    onClick = { openDialog.value = false}, //send capacity somewhere and
                    shape = CircleShape
                ) {
                    Text(text = "Submit")
                }
            }
        }
    }
}