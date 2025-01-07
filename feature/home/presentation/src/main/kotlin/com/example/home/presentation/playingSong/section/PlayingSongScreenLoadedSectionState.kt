package com.example.home.presentation.playingSong.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.media3.common.MediaItem
import com.example.home.domain.model.Song
import kotlinx.coroutines.flow.StateFlow

internal data class PlayingSongScreenLoadedSectionState(
    val currentSong: StateFlow<MediaItem?>,
    val isMediaPlayerReady: Boolean,
) : PlayingSongScreenSection

@Composable
internal fun rememberPlayingSongScreenLoadedSectionState(
    currentSong: StateFlow<MediaItem?>,
    isMediaPlayerReady: Boolean,
): PlayingSongScreenLoadedSectionState {
    return remember(currentSong, isMediaPlayerReady) {
        PlayingSongScreenLoadedSectionState(
            currentSong = currentSong,
            isMediaPlayerReady = isMediaPlayerReady,
        )
    }
}