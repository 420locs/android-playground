package com.example.sample.trash

import com.example.network.NetworkApi

class PlaceHolderApi: NetworkApi() {
    override val baseUrl: String
        get() = "https://jsonplaceholder.typicode.com/"
}