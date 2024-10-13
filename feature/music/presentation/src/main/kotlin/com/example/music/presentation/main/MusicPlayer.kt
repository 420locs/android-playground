package com.example.music.presentation.main

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.music.presentation.main.section.MusicPlayerInitialSection
import com.example.music.presentation.main.section.MusicPlayerInitialSectionState
import com.example.music.presentation.main.section.MusicPlayerLoadedSection
import com.example.music.presentation.main.section.MusicPlayerLoadedSectionState
import kotlinx.coroutines.flow.flow
import org.koin.androidx.compose.koinViewModel

@Composable
fun MusicPlayer(
    modifier: Modifier = Modifier,
    viewModel: MusicPlayerViewModel = koinViewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity),
) {
    val state = rememberMusicPlayerState(viewModel)
    MusicPlayerContent(
        modifier = modifier,
        state = state
    )
}

@Composable
private fun MusicPlayerContent(
    state: MusicPlayerState,
    modifier: Modifier = Modifier,
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

@Preview
@Composable
internal fun MusicPlayerLoadedSectionPreview() {
    val state = MusicPlayerState(
        section = MusicPlayerLoadedSectionState(
            isPlaying = true,
            durationString = "02:20",
            progress = 0.5f,
            progressString = "01:10",
            currentMediaItem = flow { },
            onStopMediaPlayer = {},
            onPlay = {},
            onPause = {},
            onPrevious = {},
            onNext = {},
        )
    )
    MusicPlayerContent(
        state = state
    )
}

@Preview
@Composable
internal fun MusicPlayerInitialSectionPreview() {
    val state = MusicPlayerState(
        section = MusicPlayerInitialSectionState(
            isError = false,
            message = "heheh",
        )
    )
    MusicPlayerContent(
        state = state
    )
}
