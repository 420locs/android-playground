package com.example.music.presentation.main

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.music.presentation.main.section.MusicPlayerInitialSection
import com.example.music.presentation.main.section.MusicPlayerInitialSectionState
import com.example.music.presentation.main.section.MusicPlayerLoadedSection
import com.example.music.presentation.main.section.MusicPlayerLoadedSectionState
import kotlinx.coroutines.flow.flow
import org.koin.androidx.compose.koinViewModel

@Composable
fun MusicPlayer(
    isCompactMode: Boolean,
    modifier: Modifier = Modifier,
    viewModel: MusicPlayerViewModel = koinViewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity),
) {
    val state = rememberMusicPlayerState(viewModel)

    if (isCompactMode) {
        MusicPlayerCompactContent(
            modifier = modifier,
            state = state
        )
    } else {
        MusicPlayerContent(
            modifier = modifier,
            state = state
        )
    }
}

@Composable
private fun MusicPlayerCompactContent(
    state: MusicPlayerState,
    modifier: Modifier = Modifier,
) {
    val sectionState = state.section as? MusicPlayerLoadedSectionState ?: return
    val song = sectionState.currentMediaItem.collectAsState(null).value
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .padding(4.dp)
                    .size(40.dp)
                    .clip(RoundedCornerShape(4.dp)),
                model = song?.mediaMetadata?.artworkUri,
                contentDescription = song?.mediaMetadata?.title.toString(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = song?.mediaMetadata?.displayTitle?.toString().orEmpty(),
                    color = Color.White,
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 14.sp,
                )
                Text(
                    text = song?.mediaMetadata?.artist?.toString().orEmpty(),
                    color = Color.Gray,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 14.sp,
                )
            }

            if (sectionState.isPlaying) {
                Icon(
                    modifier = Modifier
                        .clickable(onClick = sectionState.onPause)
                        .padding(4.dp),
                    painter = painterResource(id = android.R.drawable.ic_media_pause),
                    contentDescription = "Pause music",
                    tint = Color.White
                )
            } else {
                Icon(
                    modifier = Modifier
                        .clickable(onClick = sectionState.onPlay)
                        .padding(4.dp),
                    painter = painterResource(id = android.R.drawable.ic_media_play),
                    contentDescription = "Play music",
                    tint = Color.White
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = sectionState.progress)
                .height(2.dp)
                .background(color = Color.Gray)
                .align(Alignment.BottomStart)
        )
    }
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

@Preview
@Composable
fun MusicPlayerCompactPreview() {
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
    MusicPlayerCompactContent(
        state = state
    )

}