package com.otiutiunnyk.diploma.parking_app.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.colorResource
import com.otiutiunnyk.diploma.parking_app.R

@Composable
fun SubmitFab(openNewParkingRequestDialog: MutableState<Boolean>) {
    FloatingActionButton(
        onClick = { openNewParkingRequestDialog.value = true },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = colorResource(R.color.purple_500)
    ) {
        Icon(imageVector = Icons.Outlined.Done, contentDescription = "")
    }
}
