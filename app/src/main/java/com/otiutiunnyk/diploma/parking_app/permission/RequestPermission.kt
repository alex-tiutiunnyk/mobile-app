package com.otiutiunnyk.diploma.parking_app.permission

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.otiutiunnyk.diploma.parking_app.ui.ParkingApp

@ExperimentalPermissionsApi
@Composable
fun RequestPermission(
    permission: String,
    rationaleMessage: String = "To use this app's functionalities, you need to give us the permission.",
) {
    val permissionState = rememberPermissionState(permission)

    HandleRequest(
        permissionState = permissionState,
        content = {
            PermissionDeniedContent(
                rationaleMessage = rationaleMessage
            ) { permissionState.launchPermissionRequest() }
        }
    )
}

@ExperimentalPermissionsApi
@Composable
fun HandleRequest(
    permissionState: PermissionState,
    content: @Composable () -> Unit
) {
    when (permissionState.status) {
        is PermissionStatus.Granted -> {
            ParkingApp()
        }
        is PermissionStatus.Denied -> {
            content()
        }
    }
}

@ExperimentalPermissionsApi
@Composable
fun PermissionDeniedContent(
    showButton: Boolean = true,
    rationaleMessage: String,
    onClick: () -> Unit
) {
    if (showButton) {
        val enableLocation = remember { mutableStateOf(true) }
        if (enableLocation.value) {
            AlertDialog(
                onDismissRequest = { enableLocation.value = false },
                title = {
                    Text(
                        text = "Permission Request",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                text = {
                    Text(rationaleMessage)
                },
                confirmButton = {
                    Button(onClick = onClick) {
                        Text("Give Permission")
                    }
                }
            )
        }
    }
}