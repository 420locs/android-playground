package com.example.music.presentation.main.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal data class MusicPlayerInitialSectionState(
    val isError: Boolean,
    val message: String,
) : MusicPlayerSectionState

@Composable
internal fun rememberMusicPlayerInitialSectionState(
    isError: Boolean,
    message: String,
): MusicPlayerInitialSectionState {
    return remember(isError, message) {
        MusicPlayerInitialSectionState(
            isError = isError,
            message = message,
        )
    }
}