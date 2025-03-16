package com.example.home.presentation

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.example.home.domain.model.Song

fun songToMediaItem(song: Song): MediaItem {
    return MediaItem.Builder()
        .setUri(song.source)
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setIsBrowsable(false)
                .setMediaType(MediaMetadata.MEDIA_TYPE_MUSIC)
                .setArtist(song.artist)
                .setArtworkUri(Uri.parse(song.image))
                .setAlbumTitle(song.album)
                .setDisplayTitle(song.title)
                .build()
        ).build()
}