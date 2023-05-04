package com.otiutiunnyk.diploma.parking_app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DrawerMenuData(
    val route: String? = null, val icon: ImageVector? = null, val title: String? = null, val isDivider: Boolean = false
) {
    object AccountSettings : DrawerMenuData(
        "account settings",
        icon = Icons.Outlined.ManageAccounts,
        title = "Account Settings") //Manage Accounts

    object GeneralSettings : DrawerMenuData(
        "general settings",
        icon = Icons.Outlined.Settings,
        title = "General Settings")

    object History : DrawerMenuData(
        "history",
        icon = Icons.Outlined.History,
        title = "History") //icon Timeline?

    object InviteFriend : DrawerMenuData(
        "invite friend",
        icon = Icons.Outlined.GroupAdd,
        title = "Invite a Friend")

    object ReportIssue : DrawerMenuData(
        "report issue",
        icon = Icons.Outlined.SmsFailed,
        title = "Report an issue")

    object About : DrawerMenuData(
        "about",
        icon = Icons.Outlined.Info,
        title = "About us")

    object SignOut : DrawerMenuData(
        "sign out",
        icon = Icons.Outlined.Logout,
        title = "Sign Out")

    object Divider : DrawerMenuData(isDivider = true)
}