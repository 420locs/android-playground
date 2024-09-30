package com.example.trashArchitecture

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
                when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
//                        RequestPermissions(
//                            permission = android.Manifest.permission.POST_NOTIFICATIONS,
//                            doOnPermissionGranted = { },
//                            doOnPermissionDenied = { },
//                            //                    showRationale = { }, // todo: design rationale dialog later
//                        )

                        RequestPermissions(
                            permission = android.Manifest.permission.READ_MEDIA_AUDIO,
                            doOnPermissionGranted = { },
                            doOnPermissionDenied = { },
                            //                    showRationale = { }, // todo: design rationale dialog later
                        )
                    }

                    else -> {
                        RequestPermissions(
                            permission = android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            doOnPermissionGranted = { },
                            doOnPermissionDenied = { },
                            //                    showRationale = { }, // todo: design rationale dialog later
                        )
                    }
                }
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