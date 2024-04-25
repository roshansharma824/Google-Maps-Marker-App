package com.example.googlemapsmarkerapp.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.googlemapsmarkerapp.navigation.screen.BottomNavItemScreen
import com.example.googlemapsmarkerapp.presentation.screens.home.HomeScreen
import com.example.googlemapsmarkerapp.presentation.screens.SavedScreen


@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomNavItemScreen.Home.route
    ) {
        composable(route = BottomNavItemScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomNavItemScreen.Saved.route) {
            SavedScreen()
        }

    }
}