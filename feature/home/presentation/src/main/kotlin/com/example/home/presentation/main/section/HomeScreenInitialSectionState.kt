package com.example.home.presentation.main.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal data class HomeScreenInitialSectionState(
    val isFailed: Boolean,
    val errorMessage: String,
    val actionReload: () -> Unit
) : HomeScreenSection

@Composable
internal fun rememberHomeScreenInitialSectionState(
    isFailed: Boolean,
    errorMessage: String,
    actionReload: () -> Unit
): HomeScreenInitialSectionState {
    return remember(errorMessage, actionReload, isFailed) {
        HomeScreenInitialSectionState(isFailed, errorMessage, actionReload)
    }
}