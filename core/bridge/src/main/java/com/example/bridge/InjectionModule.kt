package com.example.bridge

import com.example.sample.MyClass
import com.example.network.NetworkApi
import com.example.sample.trash.PlaceHolderApi
import org.koin.dsl.module

val dataModule = module {
    single<NetworkApi> { PlaceHolderApi() }
}

val sampleModule = module {
    factory<MyClass> {
        MyClass(get())
    }
}