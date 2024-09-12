package com.example.bridge.core

import com.example.network.NetworkApi
import com.example.sample.data.trash.PlaceHolderApi
import org.koin.dsl.module

internal val coreDataModules = module {
    single<NetworkApi> { PlaceHolderApi() }
}