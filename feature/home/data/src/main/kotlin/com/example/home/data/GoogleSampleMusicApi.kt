package com.example.home.data

import com.example.network.NetworkApi

class GoogleSampleMusicApi: NetworkApi() {
    override val baseUrl: String = "https://storage.googleapis.com/uamp/catalog.json"
}