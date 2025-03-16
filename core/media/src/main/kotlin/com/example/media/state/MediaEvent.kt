package com.example.media.state

sealed interface MediaEvent {
    data object Play : MediaEvent
    data object Pause : MediaEvent
    data object SeekBackward : MediaEvent
    data object SeekForward : MediaEvent
    data object Next : MediaEvent
    data object Previous : MediaEvent
    data object Stop : MediaEvent
    data class UpdateProgress(val newProgress: Float) : MediaEvent
}