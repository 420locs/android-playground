package com.example.home.presentation.playingSong

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.home.presentation.playingSong.section.PlayingSongScreenSection
import com.example.home.presentation.playingSong.section.rememberPlayingSongScreenLoadedSectionState

internal data class PlayingSongScreenState(
    val section: PlayingSongScreenSection,
    val navigateBack: () -> Unit,
)

@Composable
internal fun rememberPlayingSongScreenState(
    viewModel: PlayingSongViewModel,
    navController: NavController,
): PlayingSongScreenState {
    val section = rememberPlayingSongScreenLoadedSectionState(
        currentSong = viewModel.currentSong,
        isMediaPlayerReady = true
    )
    return remember(section, navController) {
        PlayingSongScreenState(
            section = section,
            navigateBack = navController::navigateUp,
        )
    }
}