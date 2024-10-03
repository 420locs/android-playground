package com.example.media

import androidx.annotation.OptIn
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import com.example.media.notification.MediaNotifier
import com.example.media.notification.MediaSessionCallback
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object MediaModule {
    @OptIn(UnstableApi::class)
    operator fun invoke() = module {
        singleOf(::MediaServiceConnection)
        single<Player> { ExoPlayer.Builder(get()).build() }
        single<MediaSession> {
            val mediaSessionCallback = MediaSessionCallback(get())
            MediaSession.Builder(get(), get())
                .setCallback(mediaSessionCallback)
                .setCustomLayout(mediaSessionCallback.getCustomLayout())
                .build()
        }
        singleOf(::MediaNotifier)
        singleOf(::MediaPlayerHandler)
        singleOf(::MusicPool)
    }
}