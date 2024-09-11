package com.example.sample

import com.example.sample.trash.PlaceHolderService
import com.example.sample.trash.Post
import com.example.network.NetworkApi

class MyClass(private val networkApi: NetworkApi) {
    
    suspend operator fun invoke(): List<Post> {
        val api =  networkApi.createService<PlaceHolderService>()
        return api.getPosts()
    }
}