package com.example.music.presentation.main.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.media.state.MediaEvent

internal data class MusicPlayerLoadedSectionState(
    val isPlaying: Boolean,
    val durationString: String,
    val progress: Float,
    val progressString: String,
    val onMediaEvent: (MediaEvent) -> Unit,
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
    onMediaEvent: (MediaEvent) -> Unit,
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
        onMediaEvent,
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
            onMediaEvent = onMediaEvent,
            onPlay = onPlay,
            onPause = onPause,
            onPrevious = onPrevious,
            onNext = onNext,
        )
    }
}
