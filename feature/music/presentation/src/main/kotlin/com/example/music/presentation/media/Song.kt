package com.example.music.presentation.media

import android.net.Uri

data class Song(
    val title: String,
    val artist: String,
    val album: String,
    val cover: String,
    val uri: Uri,
    val fileName: String,
)
