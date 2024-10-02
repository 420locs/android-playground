package com.example.music.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.media3.session.MediaController
import com.example.home.domain.model.Song
import com.example.music.presentation.main.section.MusicPlayerInitialSection
import com.example.music.presentation.main.section.MusicPlayerInitialSectionState
import com.example.music.presentation.main.section.MusicPlayerLoadedSection
import com.example.music.presentation.main.section.MusicPlayerLoadedSectionState
import org.koin.androidx.compose.koinViewModel

@Composable
fun MusicPlayer(
    modifier: Modifier = Modifier,
    listSongs: List<Song>,
    viewModel: MusicPlayerViewModel = koinViewModel(),
) {
    val state = rememberMusicPlayerState(viewModel)
    LaunchedEffect(key1 = viewModel) {
        viewModel.loadData(listSongs)
    }
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