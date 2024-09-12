package com.example.sample.domain

import com.example.sample.domain.model.Post

interface SampleRepository {
    suspend fun getListPosts(): List<Post>
}