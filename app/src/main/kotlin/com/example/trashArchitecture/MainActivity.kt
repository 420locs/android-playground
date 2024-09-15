package com.example.trashArchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.home.presentation.navigation.HomeNavigation
import com.example.home.presentation.navigation.home
import com.example.sample.presentation.navigation.navigateToSample
import com.example.sample.presentation.navigation.sample
import com.example.designSystem.theme.TrashArchitectureTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TrashArchitectureTheme {
                NavHost(navController = navController, startDestination = HomeNavigation) {
                    home(
                        navController = navController,
                        navigateToSample = navController::navigateToSample
                    )
                    sample(navController = navController)
                }
            }
        }
    }
}