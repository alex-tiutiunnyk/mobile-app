package com.otiutiunnyk.diploma.parking_app.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.otiutiunnyk.diploma.parking_app.R

@Composable
fun LocationFab() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = colorResource(R.color.purple_500)
    ) {
        Icon(imageVector = Icons.Outlined.MyLocation, contentDescription = "")
    }
}

@Preview(showBackground = true)
@Composable
fun LocationFabPreview() {
    LocationFab()
}