package com.example.music.presentation.main.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.music.presentation.mediaProgressBar.MediaProgressBar

@Composable
internal fun MusicPlayerLoadedSection(
    state: MusicPlayerLoadedSectionState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        MediaProgressBar(
            progress = state.progress,
            durationString = state.durationString,
            progressString = state.progressString,
            onStopMediaPlayer = state.onStopMediaPlayer
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(onClick = state.onPrevious) {
                Text(text = "Previous")
            }
            Button(onClick = if (state.isPlaying) state.onPause else state.onPlay) {
                Text(text = if (state.isPlaying) "Pause" else "Play")
            }
            Button(onClick = state.onNext) {
                Text(text = "Next")
            }
        }
    }
}

@Preview
@Composable
internal fun MusicPlayerPreview() {
    val state = MusicPlayerLoadedSectionState(
        isPlaying = true,
        durationString = "02:20",
        progress = 0.5f,
        progressString = "01:10",
        currentMediaItem = null,
        onStopMediaPlayer = {},
        onPlay = {},
        onPause = {},
        onPrevious = {},
        onNext = {},
    )
    MusicPlayerLoadedSection(
        state = state
    )
}