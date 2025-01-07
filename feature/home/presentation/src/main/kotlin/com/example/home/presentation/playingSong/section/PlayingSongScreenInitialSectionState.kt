package com.example.home.presentation.playingSong.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal data class PlayingSongScreenInitialSectionState(
    val isFailed: Boolean,
    val errorMessage: String,
    val actionReload: () -> Unit
) : PlayingSongScreenSection

@Composable
internal fun rememberPlayingSongScreenInitialSectionState(
    isFailed: Boolean,
    errorMessage: String,
    actionReload: () -> Unit
): PlayingSongScreenInitialSectionState {
    return remember(errorMessage, actionReload, isFailed) {
        PlayingSongScreenInitialSectionState(isFailed, errorMessage, actionReload)
    }
}