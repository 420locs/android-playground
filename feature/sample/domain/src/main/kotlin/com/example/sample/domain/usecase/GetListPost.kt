package com.example.sample.domain.usecase

import com.example.sample.domain.SampleRepository
import com.example.sample.domain.model.Post

class GetListPost(private val sampleRepository: SampleRepository) {
    suspend operator fun invoke(): List<Post> {
        return sampleRepository.getListPosts()
    }
}