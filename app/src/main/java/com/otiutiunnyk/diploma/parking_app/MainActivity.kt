package com.otiutiunnyk.diploma.parking_app

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.location.*
import com.otiutiunnyk.diploma.parking_app.api.models.User
import com.otiutiunnyk.diploma.parking_app.api.models.viewModels.UserViewModel
import com.otiutiunnyk.diploma.parking_app.permission.RequestPermission
import com.otiutiunnyk.diploma.parking_app.ui.screen.bottomNav.FavouritesScreen
import com.otiutiunnyk.diploma.parking_app.ui.theme.ParkingappTheme
import java.util.*


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParkingappTheme {
                //test which one will work
                val mainViewModel: UserViewModel = viewModel()
                FavouritesScreen(userList = mainViewModel.usersListResponse)
                mainViewModel.getUserList()
                val testing: List<User> = mainViewModel.usersListResponse
                println("Main function: $testing")
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    RequestPermission(permission = ACCESS_FINE_LOCATION)
                }
            }
        }
    }
}
//    @SuppressLint("MissingPermission")
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String?>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            1000 -> {
//
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.isNotEmpty()
//                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
//                ) {
//                    fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
//                        if (location != null) {
//                            wayLatitude = location.latitude
//                            wayLongitude = location.longitude
//                        }
//                    }
//                } else {
//                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//}
