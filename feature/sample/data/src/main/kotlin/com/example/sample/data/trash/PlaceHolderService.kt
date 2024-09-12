package com.example.sample.data.trash

import com.example.sample.domain.model.Post
import retrofit2.http.GET

interface PlaceHolderService {

    @GET("posts")
    suspend fun getPosts(): List<Post>

}
