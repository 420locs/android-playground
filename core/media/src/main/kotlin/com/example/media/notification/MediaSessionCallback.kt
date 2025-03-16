package com.example.media.notification

import android.os.Bundle
import android.util.Log
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.CommandButton
import androidx.media3.session.MediaSession
import androidx.media3.session.SessionCommand
import androidx.media3.session.SessionResult
import com.example.media.MediaServiceConnection
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture

class MediaSessionCallback(
    private val mediaServiceConnection: MediaServiceConnection
) : MediaSession.Callback {
    private val customCommandCloses = SessionCommand(MEDIA_ACTION_CLOSE, Bundle.EMPTY)
    private val customCommandFavorites = SessionCommand(MEDIA_ACTION_FAVORITE, Bundle.EMPTY)

    @OptIn(UnstableApi::class)
    override fun onConnect(
        session: MediaSession,
        controller: MediaSession.ControllerInfo
    ): MediaSession.ConnectionResult {
        // Set available player and session commands.
        return MediaSession.ConnectionResult.AcceptedResultBuilder(session)
            .setAvailablePlayerCommands(
                MediaSession.ConnectionResult.DEFAULT_PLAYER_COMMANDS.buildUpon()
//                    .remove(COMMAND_SEEK_TO_NEXT)
//                    .remove(COMMAND_SEEK_TO_NEXT_MEDIA_ITEM)
//                    .remove(COMMAND_SEEK_TO_PREVIOUS)
//                    .remove(COMMAND_SEEK_TO_PREVIOUS_MEDIA_ITEM)
                    .build()
            )
            .setAvailableSessionCommands(
                MediaSession.ConnectionResult.DEFAULT_SESSION_COMMANDS.buildUpon()
                    .add(customCommandCloses)
                    .add(customCommandFavorites)
                    .build()
            )
            .build()
    }

    override fun onCustomCommand(
        session: MediaSession,
        controller: MediaSession.ControllerInfo,
        customCommand: SessionCommand,
        args: Bundle
    ): ListenableFuture<SessionResult> {
        return when (customCommand.customAction) {
            MEDIA_ACTION_CLOSE -> {
                mediaServiceConnection.destroyService()
                Futures.immediateFuture(SessionResult(SessionResult.RESULT_SUCCESS))
            }
            MEDIA_ACTION_FAVORITE -> {
                Log.d("NinhTBM", "Actually favor this song!")
                Futures.immediateFuture(SessionResult(SessionResult.RESULT_SUCCESS))
            }

            else -> super.onCustomCommand(session, controller, customCommand, args)
        }
    }

    internal fun getCustomLayout(): List<CommandButton>{
        val closeCommandButton = CommandButton.Builder()
            .setDisplayName("Close music")
            .setIconResId(android.R.drawable.ic_menu_close_clear_cancel)
            .setSessionCommand(customCommandFavorites)
            .build()
        val favoriteCommandButton = CommandButton.Builder()
            .setDisplayName("Save to favorite")
            .setIconResId(android.R.drawable.ic_input_add)
            .setSessionCommand(customCommandFavorites)
            .build()
        return listOf(closeCommandButton, favoriteCommandButton)
    }

    companion object {
        private const val MEDIA_ACTION_CLOSE = "MediaActionClose"
        private const val MEDIA_ACTION_FAVORITE = "MediaActionFavorite"
    }
}
