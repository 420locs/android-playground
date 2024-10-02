package com.example.music.presentation.main

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.media3.session.MediaController
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
    val duration = viewModel.duration
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


    val section = if (isReady) {
        rememberMusicPlayerLoadedSectionState(
            isPlaying = isPlaying,
            onPlay = {
                viewModel.mediaPlayer.prepare()
                viewModel.mediaPlayer.play()
            },
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