package com.example.home.presentation.main.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home.domain.model.Song
import com.example.home.presentation.songToMediaItem
import com.example.music.presentation.main.MusicPlayer

@Composable
internal fun HomeScreenLoadedSection(
    state: HomeScreenLoadedSectionState,
    outerPadding: PaddingValues,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(outerPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            HorizontalListSong(
                title = "Recently Played",
                songs = state.recentlyPlayed,
                actionPlay = { index ->
                    val newMediaItems = state.recentlyPlayed.map(::songToMediaItem)
                    state.onPlayMediaAtNewList(index, newMediaItems)
                }
            )
            HorizontalListSong(
                modifier = Modifier.padding(top = 4.dp),
                title = "Today Hits",
                songs = state.todayHits,
                actionPlay = { index ->
                    val newMediaItems = state.todayHits.map(::songToMediaItem)
                    state.onPlayMediaAtNewList(index, newMediaItems)
                }
            )
            HorizontalListSong(
                modifier = Modifier.padding(top = 4.dp),
                title = "Weekly Discovery",
                songs = state.weeklyDiscovery,
                actionPlay = { index ->
                    val newMediaItems = state.weeklyDiscovery.map(::songToMediaItem)
                    state.onPlayMediaAtNewList(index, newMediaItems)
                }
            )
            if (state.isMediaPlayerReady) {
                Spacer(modifier = Modifier.size(120.dp))
            }
        }
        if (state.isMediaPlayerReady) {
            MusicPlayer(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }

}

@Composable
private fun HorizontalListSong(
    title: String,
    songs: List<Song>,
    actionPlay: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = Modifier.padding(top = 20.dp, start = 24.dp),
        text = title,
        color = Color.Black,
        fontSize = 24.sp,
        fontWeight = FontWeight.W600,
    )
    LazyRow(modifier = modifier) {
        items(songs.size) { index ->
            Card(
                onClick = {
                    actionPlay(index)
                },
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 8.dp)
                    .padding(start = if (index == 0) 16.dp else 0.dp)
                    .padding(end = if (index == songs.lastIndex) 16.dp else 0.dp)
            ) {
                Column {
                    Text(
                        modifier = Modifier.size(width = 256.dp, height = 200.dp),
                        text = "song image",
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        text = songs[index].title.orEmpty(),
                        color = Color.Black,
                        fontWeight = FontWeight.W500,
                        fontSize = 20.sp
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 20.dp),
                        text = songs[index].artist.orEmpty(),
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
internal fun HomeScreenLoadedSectionPreview() {
    val sampleSongs = List(4) {
        Song(
            id = "",
            title = "DNA.",
            album = "",
            artist = "Kendrick Lamar",
            genre = "",
            source = "",
            image = "",
            trackNumber = 1,
            totalTrackCount = 1,
            duration = 120,
            site = "",
        )
    }
    val state = HomeScreenLoadedSectionState(
        recentlyPlayed = sampleSongs,
        todayHits = sampleSongs,
        weeklyDiscovery = sampleSongs,
        onPlayMediaAtNewList = { _, _ -> },
        isMediaPlayerReady = false,
    )
    HomeScreenLoadedSection(state, PaddingValues())
}
