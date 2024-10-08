package com.example.music.presentation.main

import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.example.media.MediaPlayerHandler
import com.example.media.MediaServiceConnection
import com.example.media.state.MediaEvent
import com.example.media.state.MediaState
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.concurrent.TimeUnit

@OptIn(SavedStateHandleSaveableApi::class)
class MusicPlayerViewModel(
    savedStateHandle: SavedStateHandle,
    val musicServiceConnection: MediaServiceConnection,
    private val musicPlayerHandler: MediaPlayerHandler
) : ViewModel() {

    private var duration by savedStateHandle.saveable { mutableLongStateOf(0L) }
    var durationString by savedStateHandle.saveable { mutableStateOf("00:00") }
        private set
    var progress by savedStateHandle.saveable { mutableFloatStateOf(0f) }
        private set
    var progressString by savedStateHandle.saveable { mutableStateOf("00:00") }
        private set
    var isPlaying by savedStateHandle.saveable { mutableStateOf(false) }
        private set
    var isReady by savedStateHandle.saveable { mutableStateOf(false) }
        private set
    val currentMediaItem = musicPlayerHandler.currentMediaItem

    init {
        viewModelScope.launch {
            musicPlayerHandler.mediaState.collect { mediaState ->
                when (mediaState) {
                    is MediaState.Buffering -> calculateProgressValues(mediaState.progress)
                    is MediaState.Initial -> isReady = false
                    is MediaState.Playing -> isPlaying = true
                    is MediaState.Progress -> calculateProgressValues(mediaState.progress)
                    is MediaState.Ready -> {
                        duration = mediaState.duration
                        durationString = formatDuration(mediaState.duration)
                        isReady = true
                    }

                    is MediaState.Pausing -> isPlaying = false
                }
            }
        }
    }

    override fun onCleared() {
        viewModelScope.launch {
            musicPlayerHandler.onMediaEvent(MediaEvent.Stop)
        }
    }

    fun onMediaEvent(mediaEvent: MediaEvent) {
        viewModelScope.launch {
            when (mediaEvent) {
                is MediaEvent.UpdateProgress -> {
                    progress = mediaEvent.newProgress
                    musicPlayerHandler.onMediaEvent(
                        MediaEvent.UpdateProgress(
                            mediaEvent.newProgress
                        )
                    )
                }

                else -> musicPlayerHandler.onMediaEvent(mediaEvent)
            }
        }
    }

    private fun formatDuration(duration: Long): String {
        val minutes: Long = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS)
        val seconds: Long = (TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS)
                - minutes * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES))
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
    }

    private fun calculateProgressValues(currentProgress: Long) {
        progress = if (currentProgress > 0) (currentProgress.toFloat() / duration) else 0f
        progressString = formatDuration(currentProgress)
    }
}