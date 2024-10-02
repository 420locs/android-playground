package com.example.home.data

import com.example.home.data.entity.SongResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import retrofit2.http.GET

class GoogleSampleMusicService(private val httpClient: HttpClient) {
    suspend fun getAllSongs() = httpClient.get("https://storage.googleapis.com/uamp/catalog.json")
}