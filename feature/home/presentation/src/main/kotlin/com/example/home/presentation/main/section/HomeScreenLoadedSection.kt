package com.example.home.presentation.main.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.music.presentation.main.MusicPlayer

@Composable
internal fun HomeScreenLoadedSection(
    state: HomeScreenLoadedSectionState,
    outerPadding: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(outerPadding)
    ) {
        LazyColumn(modifier = Modifier.weight(0.8f)) {
            items(state.listMusic.size) { index ->
                Button(onClick = {
                    state.onPlayAt(index)
                }) {
                    Text(text = state.listMusic[index].title.orEmpty())
                }
            }
        }
        MusicPlayer(
            modifier = Modifier.weight(0.2f),
            listSongs = state.listMusic
        )
    }
}

@Preview
@Composable
internal fun HomeScreenLoadedSectionPreview() {
    val state = HomeScreenLoadedSectionState(
        listMusic = emptyList(),
        onPlayAt = {}
    )
    HomeScreenLoadedSection(state, PaddingValues())

}