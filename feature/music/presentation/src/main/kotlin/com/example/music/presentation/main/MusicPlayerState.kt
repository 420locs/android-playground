package com.example.music.presentation.main

import android.net.Uri
import androidx.compose.runtime.Composable
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
    mediaController: MediaController?,
): MusicPlayerState {
    val section = when {
        mediaController == null -> rememberMusicPlayerInitialSectionState(
            isError = true,
            message = "Dek hieu sao sai????"
        )

        else -> rememberMusicPlayerLoadedSectionState(
            mediaController = mediaController,
        )
    }
    return remember(mediaController, section) {
        MusicPlayerState(
            section = section,
        )
    }
}