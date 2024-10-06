package com.example.media.state

import androidx.media3.common.MediaItem

sealed interface MediaState {
    data object Initial : MediaState
    data class Ready(val duration: Long, val currentMediaItem: MediaItem?) : MediaState
    data class Progress(val progress: Long) : MediaState
    data class Buffering(val progress: Long) : MediaState
    data object Playing : MediaState
    data object Pausing : MediaState
}