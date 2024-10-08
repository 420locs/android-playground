package com.example.media

import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.media.state.MediaEvent
import com.example.media.state.MediaState
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MediaPlayerHandler(
    private val player: Player
) : Player.Listener {

    private val _mediaState = MutableStateFlow<MediaState>(MediaState.Initial)
    val mediaState = _mediaState.asStateFlow()

    private val _currentMediaItem = MutableStateFlow(player.currentMediaItem)
    val currentMediaItem = _currentMediaItem.asStateFlow()

    init {
        player.addListener(this)
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        when (playbackState) {
            ExoPlayer.STATE_BUFFERING -> _mediaState.value =
                MediaState.Buffering(player.currentPosition)

            ExoPlayer.STATE_READY -> {
                Log.d("MediaPlayerHandler", "Co spam k?")
                _currentMediaItem.update { player.currentMediaItem }
                _mediaState.value =
                    MediaState.Ready(player.duration)
            }

            else -> Unit
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onIsPlayingChanged(isPlaying: Boolean) {
        if (isPlaying) {
            _mediaState.value = MediaState.Playing
            GlobalScope.launch(Dispatchers.Main) {
                startProgressUpdate()
            }
        } else {
            _mediaState.value = MediaState.Pausing
            stopProgressUpdate()

        }
    }

    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
        super.onMediaItemTransition(mediaItem, reason)
        _currentMediaItem.update { mediaItem }
    }

    suspend fun onMediaEvent(mediaEvent: MediaEvent) {
        when (mediaEvent) {
            MediaEvent.SeekBackward -> player.seekBack()
            MediaEvent.SeekForward -> player.seekForward()
            MediaEvent.Play -> {
                if (!player.isPlaying) {
                    player.play()
                    _mediaState.value = MediaState.Playing
                    startProgressUpdate()
                }
            }

            MediaEvent.Pause -> {
                if (player.isPlaying) {
                    player.pause()
                    stopProgressUpdate()
                }
            }

            MediaEvent.Next -> player.seekToNextMediaItem()
            MediaEvent.Previous -> player.seekToPreviousMediaItem()

            MediaEvent.Stop -> stopProgressUpdate()
            is MediaEvent.UpdateProgress -> player.seekTo((player.duration * mediaEvent.newProgress).toLong())
        }
    }

    private suspend fun startProgressUpdate() {
        while (true) {
            delay(500)
            _mediaState.value = MediaState.Progress(player.currentPosition)
        }
    }

    private fun stopProgressUpdate() {
        _mediaState.value = MediaState.Pausing
    }
}