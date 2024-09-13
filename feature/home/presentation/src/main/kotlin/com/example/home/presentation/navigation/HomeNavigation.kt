package com.example.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.home.presentation.main.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeNavigation

fun NavGraphBuilder.home(
    navController: NavController,
    navigateToSample: () -> Unit
) {
    composable<HomeNavigation> {
        HomeScreen(navigateToSample)
    }
}