package com.example.music.presentation

import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import com.example.music.presentation.media.MediaNotifier
import com.example.music.presentation.media.GetAllAudioFile
import com.example.music.presentation.media.MediaPlayerHandler
import org.koin.dsl.module

object MusicModule {
    operator fun invoke() = module {
        single<GetAllAudioFile> { GetAllAudioFile(get()) }
        single<Player> { ExoPlayer.Builder(get()).build() }
        single<MediaSession> {
            MediaSession.Builder(get(), get())
                .build()
        }
        single<MediaNotifier> { MediaNotifier(get(), get()) }
        single<MediaPlayerHandler> { MediaPlayerHandler(get()) }
    }
}