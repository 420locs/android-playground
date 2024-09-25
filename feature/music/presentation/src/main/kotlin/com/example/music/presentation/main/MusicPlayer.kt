package com.example.music.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.media3.session.MediaController
import com.example.music.presentation.main.section.MusicPlayerInitialSection
import com.example.music.presentation.main.section.MusicPlayerInitialSectionState
import com.example.music.presentation.main.section.MusicPlayerLoadedSection
import com.example.music.presentation.main.section.MusicPlayerLoadedSectionState

@Composable
fun MusicPlayer(
    mediaController: MediaController?,
    modifier: Modifier = Modifier,
) {
    val state = rememberMusicPlayerState(mediaController)
    MusicPlayerContent(
        modifier = modifier,
        state = state
    )
}

@Composable
private fun MusicPlayerContent(
    state: MusicPlayerState,
    modifier: Modifier,
) {
    when (val section = state.section) {
        is MusicPlayerInitialSectionState -> MusicPlayerInitialSection(
            state = section,
            modifier = modifier
        )

        is MusicPlayerLoadedSectionState -> MusicPlayerLoadedSection(
            state = section,
            modifier = modifier
        )
    }
}