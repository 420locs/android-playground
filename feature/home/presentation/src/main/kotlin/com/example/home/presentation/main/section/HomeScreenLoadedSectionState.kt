package com.example.home.presentation.main.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal data class HomeScreenLoadedSectionState(
    val listPost: List<Any>,
) : HomeScreenSection

@Composable
internal fun rememberHomeScreenLoadedSectionState(
    listPost: List<Any>,
): HomeScreenLoadedSectionState {
    return remember(listPost) {
        HomeScreenLoadedSectionState(listPost = listPost)
    }
}