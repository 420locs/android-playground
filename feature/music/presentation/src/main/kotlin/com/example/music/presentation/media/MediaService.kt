package com.example.music.presentation.media

import android.content.Intent
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import org.koin.android.ext.android.inject

class MediaService : MediaSessionService() {
    private val mediaSession by inject<MediaSession>()
    private val mediaNotifier by inject<MediaNotifier>()

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo) = mediaSession

    override fun onTaskRemoved(rootIntent: Intent?) {
        val currentMediaSession = mediaSession
        // Get the player from the media session
        val player = currentMediaSession.player

        // Check if the player is not ready to play or there are no items in the media queue
        if (!player.playWhenReady || player.mediaItemCount == 0) {
            // Stop the service
            stopSelf()
        }
    }

    @OptIn(UnstableApi::class)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaNotifier.startNotificationService(
            mediaSessionService = this,
            mediaSession = mediaSession,
        )
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        mediaSession.run {
            // Release the player
            player.release()
            // Release the MediaSession instance
            release()
        }
        super.onDestroy()
    }


}