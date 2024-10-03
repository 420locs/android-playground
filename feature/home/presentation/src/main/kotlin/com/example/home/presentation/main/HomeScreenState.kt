package com.example.home.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.home.presentation.main.section.HomeScreenSection
import com.example.home.presentation.main.section.rememberHomeScreenInitialSectionState
import com.example.home.presentation.main.section.rememberHomeScreenLoadedSectionState
import com.example.home.presentation.main.section.rememberHomeScreenLoadingSectionState
import com.example.media.playMediaAtPlaylist

internal data class HomeScreenState(
    val section: HomeScreenSection,
    val navigateToSample: () -> Unit,
)

@Composable
internal fun rememberHomeScreenState(
    navController: NavController,
    viewModel: HomeViewModel,
    navigateToSample: () -> Unit,
): HomeScreenState {
    val isInitiateLoading = viewModel.isFirstLoadLoading
    val isRefreshLoading = viewModel.isRefreshLoading
    val todayMusics = viewModel.todayMusics
    var isMediaPlayerReady by remember { mutableStateOf(false) }

    val section = when {
        isInitiateLoading -> rememberHomeScreenLoadingSectionState()
        todayMusics != null && todayMusics.isSuccess -> rememberHomeScreenLoadedSectionState(
            listMusic = todayMusics.getOrDefault(emptyList()),
            onPlayMediaAtNewList = { index, playlist ->
                isMediaPlayerReady = playlist.isNotEmpty()
                viewModel.mediaPlayer.playMediaAtPlaylist(index, playlist)
            },
            isMediaPlayerReady = isMediaPlayerReady,
        )

        else -> rememberHomeScreenInitialSectionState(
            isFailed = true,
            errorMessage = "Something went wrong!",
            actionReload = viewModel::refreshData

        )
    }

    return remember(section, navigateToSample) {
        HomeScreenState(section = section, navigateToSample = navigateToSample)
    }
}