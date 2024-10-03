package com.example.media

import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.Player

// Extension property for Player class to get the current media items.
// It returns a list of MediaItem objects.
internal val Player.currentMediaItems: List<MediaItem>
    get() {
        return List(mediaItemCount, ::getMediaItemAt)
    }

// Extension function for Player class to update the playlist.
// It takes a list of incoming MediaItem objects, filters out the ones that are already in the playlist,
// logs the items to be added, and then adds them to the player.
fun Player.updatePlaylist(incoming: List<MediaItem>) {
    val oldMediaIds = currentMediaItems.map { it.mediaId }.toSet()
    val itemsToAdd = incoming.filterNot { item -> item.mediaId in oldMediaIds }
    Log.d("PlayerExt", "updatePlaylist: itemsToAdd: $itemsToAdd")
    addMediaItems(itemsToAdd)
}

// Extension function for Player class to play a media item at a specific index.
// If the current media item index is the same as the provided index, it does nothing.
// Otherwise, it seeks to the default position of the media item at the provided index,
// sets the player to play when ready, and prepares the player to recover from any errors
// that may have happened at previous media positions.
fun Player.playMediaAt(index: Int, isTheSamePlaylist: Boolean = false) {
    if (currentMediaItemIndex == index && isTheSamePlaylist)
        return
    seekToDefaultPosition(index)
    playWhenReady = true
    // Recover from any errors that may have happened at previous media positions
    prepare()
}

fun Player.playMediaAtPlaylist(index: Int, newList: List<MediaItem>) {
    val isPlaylistSameSize = currentMediaItems.size == newList.size
    val isTheSamePlaylist = isPlaylistSameSize && !currentMediaItems.any {
        it.mediaId != newList[index].mediaId
    }
    if (!isTheSamePlaylist) {
        clearMediaItems()
        addMediaItems(newList)
    }
    prepare()
    playMediaAt(index, isTheSamePlaylist)
}