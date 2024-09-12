package com.example.sample.data.trash

import com.example.network.NetworkApi
import com.example.sample.domain.SampleRepository
import com.example.sample.domain.model.Post

class SampleInternalRepository(networkApi: NetworkApi): SampleRepository {
    private val sampleRestApi = networkApi.createService(PlaceHolderService::class.java)
    override suspend fun getListPosts(): List<Post> {
        return sampleRestApi.getPosts()
    }
}