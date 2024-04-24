package com.example.googlemapsmarkerapp.navigation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItemScreen(val route: String, val icon: ImageVector, val title: String) {

    data object Home : BottomNavItemScreen("home_screen", Icons.Outlined.Home, "Home")

    data object Saved : BottomNavItemScreen("saved_screen", Icons.Outlined.Favorite, "Saved")


}
