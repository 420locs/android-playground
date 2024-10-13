package com.example.home.presentation.playingSong

import androidx.lifecycle.ViewModel
import com.example.media.MediaPlayerHandler

class PlayingSongViewModel(
    mediaPlayerHandler: MediaPlayerHandler
): ViewModel() {
    val currentSong = mediaPlayerHandler.currentMediaItem
}