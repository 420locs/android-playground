package com.example.home.presentation.main.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal data object HomeScreenLoadingSectionState : HomeScreenSection

@Composable
internal fun rememberHomeScreenLoadingSectionState(): HomeScreenLoadingSectionState {
    return remember {
        HomeScreenLoadingSectionState
    }
}