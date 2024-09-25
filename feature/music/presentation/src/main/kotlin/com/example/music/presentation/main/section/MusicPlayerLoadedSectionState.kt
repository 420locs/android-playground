package com.example.music.presentation.main.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.media3.common.Player.Listener
import androidx.media3.session.MediaController

internal data class MusicPlayerLoadedSectionState(
    val hasNext: Boolean,
    val hasPrevious: Boolean,
    val isPlaying: Boolean,
    val onPlay: () -> Unit,
    val onPause: () -> Unit,
    val onPrevious: () -> Unit,
    val onNext: () -> Unit,
) : MusicPlayerSectionState

@Composable
internal fun rememberMusicPlayerLoadedSectionState(
    mediaController: MediaController,
): MusicPlayerLoadedSectionState {
    var isPlaying by remember { mutableStateOf(false) }
    val onPlay = remember(mediaController) {
        {
            isPlaying = true
            mediaController.prepare()
            mediaController.play()
        }
    }
    val onPause = remember(mediaController) {
        {
            isPlaying = false
            mediaController.pause()
        }
    }
    val onNext = remember(mediaController) {
        {
            isPlaying = true
            mediaController.seekToNextMediaItem()
        }
    }
    val onPrevious = remember(mediaController) {
        {
            isPlaying = true
            mediaController.seekToPreviousMediaItem()
        }
    }
    LaunchedEffect(mediaController) {
        mediaController.addListener(object : Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {

            }
        })
    }
    return remember(mediaController, isPlaying) {
        MusicPlayerLoadedSectionState(
            hasNext = mediaController.hasNextMediaItem(),
            hasPrevious = mediaController.hasPreviousMediaItem(),
            isPlaying = isPlaying,
            onPlay = onPlay,
            onPause = onPause,
            onPrevious = onPrevious,
            onNext = onNext,
        )
    }
}