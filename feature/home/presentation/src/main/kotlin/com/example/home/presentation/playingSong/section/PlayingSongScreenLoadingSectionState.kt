package com.example.home.presentation.playingSong.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal data object PlayingSongScreenLoadingSectionState : PlayingSongScreenSection

@Composable
internal fun rememberHomeScreenLoadingSectionState(): PlayingSongScreenLoadingSectionState {
    return remember {
        PlayingSongScreenLoadingSectionState
    }
}