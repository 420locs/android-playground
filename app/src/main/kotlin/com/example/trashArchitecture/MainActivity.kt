package com.example.trashArchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.designSystem.theme.TrashArchitectureTheme
import com.example.home.presentation.navigation.HomeNavigation
import com.example.home.presentation.navigation.home
import com.example.media.MediaServiceConnection
import com.example.sample.presentation.navigation.navigateToSample
import com.example.sample.presentation.navigation.sample
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
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

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    private fun RequestPermissions(
        permission: String,
        doOnPermissionGranted: () -> Unit,
        doOnPermissionDenied: () -> Unit,
        showRationale: (() -> Unit)? = null,
    ) {
        val cameraPermissionState =
            rememberPermissionState(permission)
        val requestPermissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                doOnPermissionGranted()
            } else {
                doOnPermissionDenied()
            }
        }

        LaunchedEffect(cameraPermissionState) {
            if (!cameraPermissionState.status.isGranted && cameraPermissionState.status.shouldShowRationale) {
                showRationale?.invoke()
            } else {
                requestPermissionLauncher.launch(permission)
            }
        }
    }
}