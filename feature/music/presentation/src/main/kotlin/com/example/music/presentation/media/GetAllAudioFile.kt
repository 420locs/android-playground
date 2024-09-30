package com.example.music.presentation.media

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import android.util.Log

class GetAllAudioFile(private val context: Context) {
    // Query the MediaStore for audio files
    operator fun invoke(): List<Song> {
        if (!context.isPermissionGranted()) return emptyList()
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
        )

        val selection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            "${MediaStore.Audio.Media.IS_MUSIC} != 0 AND ${MediaStore.Audio.Media.IS_RECORDING} == 0"
        } else {
            "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        }
        val sortOrder = "${MediaStore.Audio.Media.DISPLAY_NAME} ASC"

        // Query the MediaStore
        val cursor = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            sortOrder
        )
        val listMusic = mutableListOf<Song>()

        // Retrieve the URI of a specific music file
        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val nameColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
            val titleColumn = it.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val durationColumn = it.getColumnIndex(MediaStore.Audio.Media.DURATION)
            val artistColumn = it.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val albumIdColumn = it.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)
            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val name = it.getString(nameColumn)
                val title = it.getString(titleColumn)
                val duration = it.getString(durationColumn)
                val artist = it.getString(artistColumn)
                val albumId = it.getString(albumIdColumn)
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                // contentUri now contains the URI of the local music file
                // Example: content://media/external/audio/media/12345
                Log.d("Music URI", contentUri.toString())
                val song = Song(
                    title = title,
                    artist = duration,
                    album = artist,
                    cover = albumId,
                    uri = contentUri,
                    fileName = name,
                )
                listMusic.add(song)
            }
        }
        return listMusic
    }

    private fun Context.isPermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkSelfPermission(Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED
        } else {
            checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        }
    }
}