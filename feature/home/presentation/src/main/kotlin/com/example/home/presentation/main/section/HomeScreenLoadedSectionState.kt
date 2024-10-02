package com.example.home.presentation.main.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.home.domain.model.Song

internal data class HomeScreenLoadedSectionState(
    val listMusic: List<Song>,
    val onPlayAt: (Int) -> Unit,
) : HomeScreenSection

@Composable
internal fun rememberHomeScreenLoadedSectionState(
    listMusic: List<Song>,
    onPlayAt: (Int) -> Unit,
): HomeScreenLoadedSectionState {

    return remember(listMusic) {
        HomeScreenLoadedSectionState(listMusic = listMusic, onPlayAt = onPlayAt)
    }
}