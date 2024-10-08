package com.example.home.presentation.main.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.media3.common.MediaItem
import com.example.home.domain.model.Song

internal data class HomeScreenLoadedSectionState(
    val recentlyPlayed: List<Song>,
    val todayHits: List<Song>,
    val weeklyDiscovery: List<Song>,
    val isMediaPlayerReady: Boolean,
    val onPlayMediaAtNewList: (Int, List<MediaItem>) -> Unit,
) : HomeScreenSection

@Composable
internal fun rememberHomeScreenLoadedSectionState(
    listMusic: List<Song>,
    isMediaPlayerReady: Boolean,
    onPlayMediaAtNewList: (Int, List<MediaItem>) -> Unit,
): HomeScreenLoadedSectionState {
    // fixme: Fake data
    val recentlyPlayed = remember { listMusic.shuffled().take(6) }
    val todayHits = remember { listMusic.shuffled().take(5) }
    val weeklyDiscovery = remember {
        listMusic.filterNot {
            recentlyPlayed.contains(it)
        }
    }
    return remember(listMusic, isMediaPlayerReady) {
        HomeScreenLoadedSectionState(
            recentlyPlayed = recentlyPlayed,
            todayHits = todayHits,
            weeklyDiscovery = weeklyDiscovery,
            onPlayMediaAtNewList = onPlayMediaAtNewList,
            isMediaPlayerReady = isMediaPlayerReady,
        )
    }
}