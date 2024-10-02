package com.example.music.presentation.main.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal data class MusicPlayerLoadedSectionState(
    val isPlaying: Boolean,
    val onPlay: () -> Unit,
    val onPause: () -> Unit,
    val onPrevious: () -> Unit,
    val onNext: () -> Unit,
) : MusicPlayerSectionState

@Composable
internal fun rememberMusicPlayerLoadedSectionState(
    isPlaying: Boolean,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
): MusicPlayerLoadedSectionState {
    return remember(isPlaying, onPlay, onPause, onPrevious, onNext) {
        MusicPlayerLoadedSectionState(
            isPlaying = isPlaying,
            onPlay = onPlay,
            onPause = onPause,
            onPrevious = onPrevious,
            onNext = onNext,
        )
    }
}