package com.example.home.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Song(
    @SerialName("id") var id: String?,
    @SerialName("title") var title: String?,
    @SerialName("album") var album: String?,
    @SerialName("artist") var artist: String?,
    @SerialName("genre") var genre: String?,
    @SerialName("source") var source: String?,
    @SerialName("image") var image: String?,
    @SerialName("trackNumber") var trackNumber: Int?,
    @SerialName("totalTrackCount") var totalTrackCount: Int?,
    @SerialName("duration") var duration: Int?,
    @SerialName("site") var site: String?
)