package com.example.media

import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import org.koin.dsl.module

object MediaModule {
    operator fun invoke() = module {
        single<Player> { ExoPlayer.Builder(get()).build() }
        single<MediaSession> {
            MediaSession.Builder(get(), get())
                .build()
        }
        single<MediaNotifier> { MediaNotifier(get(), get()) }
        single<MediaPlayerHandler> { MediaPlayerHandler(get()) }
        single<MediaServiceConnection> { MediaServiceConnection(get()) }
    }
}