package com.example.sample.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sample.presentation.main.SampleScreen
import kotlinx.serialization.Serializable

@Serializable
data object SampleNavigation

fun NavGraphBuilder.sample(
    navController: NavController,
) {
    composable<SampleNavigation> {
        SampleScreen(navController)
    }
}

fun NavController.navigateToSample() {
    navigate(SampleNavigation)
}
