package com.example.home.presentation.main.section

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.Listener
import androidx.media3.session.MediaController
import com.example.music.presentation.media.Song
import com.example.music.presentation.media.updatePlaylist
import com.example.music.presentation.music.rememberManagedMediaController

internal data class HomeScreenLoadedSectionState(
    val listMusic: List<Song>,
    val mediaController: MediaController?,
) : HomeScreenSection

@Composable
internal fun rememberHomeScreenLoadedSectionState(
    listMusic: List<Song>
): HomeScreenLoadedSectionState {

    val mediaController by rememberManagedMediaController()

    LaunchedEffect(mediaController) {
        mediaController?.updatePlaylist(
            listMusic.map {
                MediaItem.fromUri(it.uri)
            }
        )
    }
    return remember(listMusic, mediaController) {
        HomeScreenLoadedSectionState(listMusic = listMusic, mediaController = mediaController)
    }
}