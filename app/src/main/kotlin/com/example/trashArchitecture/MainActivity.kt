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
import com.example.home.presentation.navigation.HomeNavigation
import com.example.home.presentation.navigation.home
import com.example.sample.presentation.navigation.navigateToSample
import com.example.sample.presentation.navigation.sample
import com.example.designSystem.theme.TrashArchitectureTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TrashArchitectureTheme {
                RequestNotificationPermissions(
                    doOnPermissionGranted = { },
                    doOnPermissionDenied = { },
//                    showRationale = { }, // todo: design rationale dialog later
                )
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

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    private fun RequestNotificationPermissions(
        doOnPermissionGranted: () -> Unit,
        doOnPermissionDenied: () -> Unit,
        showRationale: (() -> Unit)? = null,
    ) {
        val cameraPermissionState =
            rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)
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
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}