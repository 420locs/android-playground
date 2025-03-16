package com.example.trashArchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.designSystem.theme.TrashArchitectureTheme
import com.example.home.presentation.navigation.HomeNavigation
import com.example.home.presentation.navigation.home
import com.example.media.MediaServiceConnection
import com.example.sample.presentation.navigation.navigateToSample
import com.example.sample.presentation.navigation.sample
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {
    private val mediaServiceConnection by inject<MediaServiceConnection>()

    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinAndroidContext {
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

    override fun onDestroy() {
        super.onDestroy()
        mediaServiceConnection.destroyService()
    }

}