package com.example.music.presentation.main

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import org.koin.androidx.compose.koinViewModel

@Composable
fun MusicPlayer(
    isCompactMode: Boolean,
    modifier: Modifier = Modifier,
    viewModel: MusicPlayerViewModel = koinViewModel(),
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
    val song = sectionState.currentMediaItem?.mediaMetadata
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .padding(4.dp)
                    .size(40.dp),
                model = sectionState.currentMediaItem?.mediaMetadata?.artworkUri,
                contentDescription = sectionState.currentMediaItem?.mediaMetadata?.title.toString(),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier,
                    text = song?.title?.toString().orEmpty(),
                    color = MaterialTheme.colorScheme.surface,
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    modifier = Modifier,
                    text = song?.artist?.toString().orEmpty(),
                    color = MaterialTheme.colorScheme.surface,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            if (sectionState.isPlaying) {
                Icon(
                    modifier = Modifier
                        .clickable(onClick = sectionState.onPause)
                        .padding(4.dp),
                    painter = painterResource(id = android.R.drawable.ic_media_pause),
                    contentDescription = "Pause music",
                    tint = MaterialTheme.colorScheme.primary
                )
            } else {
                Icon(
                    modifier = Modifier
                        .clickable(onClick = sectionState.onPlay)
                        .padding(4.dp),
                    painter = painterResource(id = android.R.drawable.ic_media_play),
                    contentDescription = "Play music",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
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
            currentMediaItem = null,
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
            currentMediaItem = null,
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