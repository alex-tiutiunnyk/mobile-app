package com.otiutiunnyk.diploma.parking_app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DrawerMenuData(
    val icon: ImageVector? = null, val title: String? = null, val isDivider: Boolean = false
) {
    object AccountSettings : DrawerMenuData(
        icon = Icons.Outlined.ManageAccounts,
        title = "Account Settings") //Manage Accounts

    object GeneralSettings : DrawerMenuData(
        icon = Icons.Outlined.Settings,
        title = "General Settings")

    object History : DrawerMenuData(
        icon = Icons.Outlined.History,
        title = "History") //Timeline?

    object InviteFriend : DrawerMenuData(
        icon = Icons.Outlined.GroupAdd,
        title = "Invite a Friend")

    object ReportIssue : DrawerMenuData(
        icon = Icons.Outlined.SmsFailed,
        title = "Report an issue")

    object About : DrawerMenuData(
        icon = Icons.Outlined.Info,
        title = "About us")

    object SignOut : DrawerMenuData(
        icon = Icons.Outlined.Logout,
        title = "Sign Out")

    object Divider : DrawerMenuData(isDivider = true)
}