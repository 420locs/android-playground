package com.example.sample.trash

import kotlinx.serialization.Serializable
import retrofit2.http.GET

interface PlaceHolderService {

    @GET("posts")
    suspend fun getPosts(): List<Post>

}

@Serializable
data class Post(
    val userId: Int?,
    val id: Int?,
    val title: String?,
    val body: String?,
)
