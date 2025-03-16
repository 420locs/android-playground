package com.example.home.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MusicResponse(
    @SerialName("music") val listMusic: List<SongResponse>?
)
