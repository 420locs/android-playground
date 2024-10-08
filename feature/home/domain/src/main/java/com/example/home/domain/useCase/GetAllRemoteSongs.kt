package com.example.home.domain.useCase

import com.example.home.domain.HomeRepository
import com.example.home.domain.model.Song

class GetAllRemoteSongs(private val homeRepository: HomeRepository) {
    suspend operator fun invoke(): List<Song> {
        return homeRepository.getListSongs()
    }
}