package com.app.keverse.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : NavigationItem("home", Icons.Default.Home, "Beranda")
    object Group : NavigationItem("group", Icons.Default.Star, "Group")
    object Profile : NavigationItem("profile", Icons.Default.Person, "Profil")
}
