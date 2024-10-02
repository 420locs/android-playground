package com.example.home.domain

import com.example.home.domain.model.Song

interface HomeRepository {
    suspend fun getListSongs(): List<Song>
}