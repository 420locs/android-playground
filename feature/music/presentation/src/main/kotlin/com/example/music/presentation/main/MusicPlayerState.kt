package com.example.music.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.example.media.state.MediaEvent
import com.example.music.presentation.main.section.MusicPlayerSectionState
import com.example.music.presentation.main.section.rememberMusicPlayerInitialSectionState
import com.example.music.presentation.main.section.rememberMusicPlayerLoadedSectionState

internal data class MusicPlayerState(
    val section: MusicPlayerSectionState,
)

@Composable
internal fun rememberMusicPlayerState(
    viewModel: MusicPlayerViewModel,
): MusicPlayerState {
    val durationString = viewModel.durationString
    val progress = viewModel.progress
    val progressString = viewModel.progressString
    val isPlaying = viewModel.isPlaying
    val isReady = viewModel.isReady
    val currentMediaItem = viewModel.currentMediaItem

    LaunchedEffect(key1 = isReady) {
        if (isReady) {
            viewModel.musicServiceConnection.startService()
        }
    }

    val onPlay = rememberMediaAction(
        key1 = viewModel,
        onMediaEvent = viewModel::onMediaEvent,
        action = MediaEvent.Play
    )
    val onPause = rememberMediaAction(
        key1 = viewModel,
        onMediaEvent = viewModel::onMediaEvent,
        action = MediaEvent.Pause
    )
    val onPrevious = rememberMediaAction(
        key1 = viewModel,
        onMediaEvent = viewModel::onMediaEvent,
        action = MediaEvent.Previous
    )
    val onNext = rememberMediaAction(
        key1 = viewModel,
        onMediaEvent = viewModel::onMediaEvent,
        action = MediaEvent.Next
    )
    val onStopMediaPlayer = remember(viewModel) {
        { newValue: Float ->
            viewModel.onMediaEvent(MediaEvent.UpdateProgress(newProgress = newValue))
        }
    }

    val section = if (isReady) {
        rememberMusicPlayerLoadedSectionState(
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
    } else {
        rememberMusicPlayerInitialSectionState(isError = false, message = "Something went wrong?")
    }
    return remember(viewModel, section) {
        MusicPlayerState(
            section = section,
        )
    }
}

@Composable
private fun rememberMediaAction(
    key1: Any?,
    onMediaEvent: (MediaEvent) -> Unit,
    action: MediaEvent
) = remember(key1) {
    {
        onMediaEvent(action)
    }
}
