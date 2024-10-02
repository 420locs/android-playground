package com.example.music.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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

    LaunchedEffect(key1 = isReady) {
        if (isReady) {
            viewModel.musicServiceConnection.startService()
        }
    }

    val onPlay = remember(viewModel.mediaPlayer) {
        {
            viewModel.mediaPlayer.prepare()
            viewModel.mediaPlayer.play()
        }
    }

    val section = if (isReady) {
        rememberMusicPlayerLoadedSectionState(
            isPlaying = isPlaying,
            durationString = durationString,
            progress = progress,
            progressString = progressString,
            onMediaEvent = viewModel::onMediaEvent,
            onPlay = onPlay,
            onPause = viewModel.mediaPlayer::pause,
            onPrevious = viewModel.mediaPlayer::seekToPreviousMediaItem,
            onNext = viewModel.mediaPlayer::seekToNextMediaItem,
        )
    } else {
        rememberMusicPlayerInitialSectionState(isError = false, message = "Something went wrong?")
    }
    return remember(section, viewModel.mediaPlayer) {
        MusicPlayerState(
            section = section,
        )
    }
}