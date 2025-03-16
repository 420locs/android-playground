package com.example.sample.data.trash

import com.example.sample.domain.SampleRepository
import com.example.sample.domain.model.Post
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode

class SampleInternalRepository(private val placeHolderService: PlaceHolderService): SampleRepository {

    override suspend fun getListPosts(): List<Post> {
        val response = placeHolderService.getPosts()

        when (response.status) {
            HttpStatusCode.OK -> {
                val musics = checkNotNull(response.body<List<Post>?>())
                return musics
            }
            else -> throw IllegalStateException("get post api failed!")
        }
    }
}