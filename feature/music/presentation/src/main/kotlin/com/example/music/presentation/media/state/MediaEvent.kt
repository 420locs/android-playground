package com.example.music.presentation.media.state

sealed interface MediaEvent {
    data object PlayPause : MediaEvent
    data object Backward : MediaEvent
    data object Forward : MediaEvent
    data object Stop : MediaEvent
    data class UpdateProgress(val newProgress: Float) : MediaEvent
}