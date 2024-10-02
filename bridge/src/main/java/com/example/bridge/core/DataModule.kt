package com.example.bridge.core

import android.util.Log
import com.example.network.NetworkApi
import com.example.network.getKtorClient
import com.example.sample.data.trash.PlaceHolderApi
import io.ktor.client.HttpClient
import org.koin.dsl.module

internal val coreDataModules = module {
    single<NetworkApi> { PlaceHolderApi() }
    single<HttpClient> {
        getKtorClient{
            Log.d("Ktor Log", it)
        }
    }
}