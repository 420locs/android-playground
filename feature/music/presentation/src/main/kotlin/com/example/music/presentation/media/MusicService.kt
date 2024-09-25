package com.example.music.presentation.media

import android.app.PendingIntent
import android.content.Intent
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.example.pushNotification.ItemNotification
import com.example.pushNotification.SystemTrayNotifier

class MusicService : MediaSessionService() {
    private var mediaSession: MediaSession? = null

    /**
     * This method is called when the service is being created.
     * It initializes the ExoPlayer and MediaSession instances.
     */
    @OptIn(UnstableApi::class)
    override fun onCreate() {
        super.onCreate() // Call the superclass method

        // Create an ExoPlayer instance
        val player = ExoPlayer.Builder(this).build()

        // Create a MediaSession instance
        mediaSession = MediaSession.Builder(this, player)
            .build()
    }

    /**
     * This method is called when the system determines that the service is no longer used and is being removed.
     * It checks the player's state and if the player is not ready to play or there are no items in the media queue, it stops the service.
     *
     * @param rootIntent The original root Intent that was used to launch the task that is being removed.
     */
    override fun onTaskRemoved(rootIntent: Intent?) {
        val currentMediaSession = mediaSession ?: return
        // Get the player from the media session
        val player = currentMediaSession.player

        // Check if the player is not ready to play or there are no items in the media queue
        if (!player.playWhenReady || player.mediaItemCount == 0) {
            // Stop the service
            stopSelf()
        }
    }

    /**
     * This method is called when a MediaSession.ControllerInfo requests the MediaSession.
     * It returns the current MediaSession instance.
     *
     * @param controllerInfo The MediaSession.ControllerInfo that is requesting the MediaSession.
     * @return The current MediaSession instance.
     */
    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

    /**
     * This method is called when the service is being destroyed.
     * It releases the player and the MediaSession instances.
     */
    override fun onDestroy() {
        // If _mediaSession is not null, run the following block
        mediaSession?.run {
            // Release the player
            player.release()
            // Release the MediaSession instance
            release()
            // Set _mediaSession to null
            mediaSession = null
        }
        // Call the superclass method
        super.onDestroy()
    }


}