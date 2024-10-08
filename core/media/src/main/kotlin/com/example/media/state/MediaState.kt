package com.example.media.state

sealed interface MediaState {
    data object Initial : MediaState
    data class Ready(val duration: Long) : MediaState
    data class Progress(val progress: Long) : MediaState
    data class Buffering(val progress: Long) : MediaState
    data object Playing : MediaState
    data object Pausing : MediaState
}