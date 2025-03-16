package com.example.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.home.presentation.main.HomeScreen
import com.example.home.presentation.playingSong.PlayingSongScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeNavigation
@Serializable
data object PlayingSongNavigation

fun NavGraphBuilder.home(
    navController: NavController,
    navigateToSample: () -> Unit
) {
    composable<HomeNavigation> {
        HomeScreen(navController = navController, navigateToHome = navigateToSample)
    }
    composable<PlayingSongNavigation> {
        PlayingSongScreen(navController = navController) //todo: implement start point to this screen (player)
    }

}