package com.example.home.presentation.playingSong.section

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.runtime.collectAsState
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow

@Composable
internal fun PlayingSongScreenLoadedSection(
    state: PlayingSongScreenLoadedSectionState,
    outerPadding: PaddingValues,
) {
    Column(
        modifier = Modifier
            .padding(outerPadding)
            .fillMaxSize()
            .background(Color.White)
    ) {
        val song = state.currentSong.collectAsState().value
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentScale = ContentScale.Crop,
            model = song?.mediaMetadata?.artworkUri,
            contentDescription = song?.mediaMetadata?.title?.toString()
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = song?.mediaMetadata?.displayTitle?.toString().orEmpty(),
            color = Color.Black,
            fontWeight = FontWeight.W500,
            fontSize = 18.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 14.sp,
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = song?.mediaMetadata?.artist?.toString().orEmpty(),
            color = Color.Gray,
            fontSize = 16.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 14.sp,
        )

        if (state.isMediaPlayerReady) {
            MusicPlayer()
        }
    }

}

@Preview
@Composable
internal fun HomeScreenLoadedSectionPreview() {
    val state = PlayingSongScreenLoadedSectionState(
        currentSong = MutableStateFlow(null).asStateFlow(),
        isMediaPlayerReady = false,
    )
    PlayingSongScreenLoadedSection(state, PaddingValues())
}
