package com.otiutiunnyk.diploma.parking_app

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.otiutiunnyk.diploma.parking_app.permission.RequestPermission
import com.otiutiunnyk.diploma.parking_app.ui.ParkingApp
import com.otiutiunnyk.diploma.parking_app.ui.theme.ParkingappTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParkingappTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    RequestPermission(permission = ACCESS_FINE_LOCATION)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ParkingappTheme {
        ParkingApp()
    }
}