package com.example.music.presentation.main.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.media3.common.MediaItem
import com.example.media.state.MediaEvent

internal data class MusicPlayerLoadedSectionState(
    val isPlaying: Boolean,
    val durationString: String,
    val progress: Float,
    val progressString: String,
    val currentMediaItem: MediaItem?,
    val onStopMediaPlayer: (Float) -> Unit,
    val onPlay: () -> Unit,
    val onPause: () -> Unit,
    val onPrevious: () -> Unit,
    val onNext: () -> Unit,
) : MusicPlayerSectionState

@Composable
internal fun rememberMusicPlayerLoadedSectionState(
    isPlaying: Boolean,
    durationString: String,
    progress: Float,
    progressString: String,
    currentMediaItem: MediaItem?,
    onStopMediaPlayer: (Float) -> Unit,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
): MusicPlayerLoadedSectionState {
    return remember(
        isPlaying,
        durationString,
        progress,
        progressString,
        currentMediaItem,
        onStopMediaPlayer,
        onPlay,
        onPause,
        onPrevious,
        onNext
    ) {
        MusicPlayerLoadedSectionState(
            isPlaying = isPlaying,
            durationString = durationString,
            progress = progress,
            progressString = progressString,
            currentMediaItem = currentMediaItem,
            onStopMediaPlayer = onStopMediaPlayer,
            onPlay = onPlay,
            onPause = onPause,
            onPrevious = onPrevious,
            onNext = onNext,
        )
    }
}
