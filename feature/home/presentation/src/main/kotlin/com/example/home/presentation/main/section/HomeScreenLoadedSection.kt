package com.example.home.presentation.main.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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
            MusicPlayer(isCompactMode = true, modifier = Modifier.align(Alignment.BottomCenter))
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
            SongItem(
                song = songs[index],
                isFirstItem = index == 0,
                isLastItem = index == songs.lastIndex,
                actionPlay = {
                    actionPlay(index)
                },
            )
        }
    }
}

@Composable
private fun SongItem(
    song: Song,
    isFirstItem: Boolean,
    isLastItem: Boolean,
    actionPlay: () -> Unit
) {
    Card(
        elevation = cardElevation(
            defaultElevation = 1.dp,
            pressedElevation = 6.dp,
            focusedElevation = 6.dp,
            hoveredElevation = 3.dp,
            draggedElevation = 8.dp,
            disabledElevation = 0.dp,
        ),
        onClick = actionPlay,
        modifier = Modifier
            .width(256.dp)
            .padding(vertical = 16.dp, horizontal = 8.dp)
            .padding(start = if (isFirstItem) 16.dp else 0.dp)
            .padding(end = if (isLastItem) 16.dp else 0.dp)
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = song.image.orEmpty(),
                contentDescription = song.title,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                text = song.title.orEmpty(),
                color = Color.Black,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 20.dp),
                text = song.artist.orEmpty(),
                color = Color.Black,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
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
            image = "https://open.spotify.com/track/6HZILIRieu8S0iqY8kIKhj",
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
