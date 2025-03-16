package com.example.sample.data.trash

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class PlaceHolderService(private val httpClient: HttpClient) {
    private val baseUrl = "https://jsonplaceholder.typicode.com/"

    suspend fun getPosts() = httpClient.get("${baseUrl}posts")

}
