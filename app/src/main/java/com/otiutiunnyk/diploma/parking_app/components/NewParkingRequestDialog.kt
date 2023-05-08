package com.otiutiunnyk.diploma.parking_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.android.gms.maps.model.LatLng
import com.otiutiunnyk.diploma.parking_app.R

@Composable
fun NewParkingRequestDialog(
    openNewParkingDialog: MutableState<Boolean>,
    newParkingPosition: LatLng
) {
    Dialog(
        onDismissRequest = { openNewParkingDialog.value = false },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        NewParkingRequestDialogUI(openNewParkingDialog, newParkingPosition)
    }
}

@Composable
fun NewParkingRequestDialogUI(
    openNewParkingRequestDialog: MutableState<Boolean>,
    newParkingPosition: LatLng
) {
    var capacity: Int by remember { mutableStateOf(0) }
    var price: Double by remember { mutableStateOf(0.0) }
    var isForDisabledPerson: Boolean by remember { mutableStateOf(false) }
    var isForElectricCar: Boolean by remember { mutableStateOf(false) }
    var comment: String by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 450.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(all = 20.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            //first row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Fill in some additional information",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                value = capacity.toString(),
                onValueChange = {
                    if (it.isEmpty() || it < 0.toString()) {
                        capacity = 0
                    } else {
                        val i = it.toIntOrNull()
                        if (i != null) {
                            capacity = i
                        }
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(text = "Capacity") }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                value = price.toString(),
                onValueChange = {
                    if (it.isEmpty() || it < 0.toString()) {
                        price = 0.0
                    } else {
                        val i = it.toDoubleOrNull()
                        if (i != null) {
                            price = i
                        }
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(text = "Price/hour") }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                value = comment,
                onValueChange = {
                    comment = it
                },
                label = { Text(text = "Comment") }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = isForDisabledPerson,
                    onCheckedChange = { isForDisabledPerson = !isForDisabledPerson },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.purple_700),
                        checkmarkColor = Color.White
                    )
                )
                Text(text = "Adapted for disabled people")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isForElectricCar,
                    onCheckedChange = { isForElectricCar = !isForElectricCar },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.purple_700),
                        checkmarkColor = Color.White
                    )
                )
                Text(text = "Adapted for electric cars")
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                value = newParkingPosition.toString(),
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Picked spot") }
            )

            //submit row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { openNewParkingRequestDialog.value = false },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    shape = CircleShape
                ) {
                    Text(text = "Cancel", color = MaterialTheme.colors.primary)
                }
                Button(
                    onClick = { openNewParkingRequestDialog.value = false }, //send data somehow
                    shape = CircleShape
                ) {
                    Text(text = "Submit")
                }
            }
        }
    }
}