package com.example.home.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class GoogleSampleMusicService(private val httpClient: HttpClient) {
    private val baseUrl = "https://storage.googleapis.com/uamp/"
    suspend fun getAllSongs() = httpClient.get("${baseUrl}catalog.json")
}