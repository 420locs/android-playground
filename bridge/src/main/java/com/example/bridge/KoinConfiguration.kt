package com.example.bridge

import android.content.Context
import android.util.Log
import com.example.bridge.feature.HomeModule
import com.example.bridge.feature.SampleModule
import com.example.media.MediaModule
import com.example.music.presentation.MusicModule
import com.example.network.getKtorClient
import io.ktor.client.HttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

object KoinConfiguration {
    private val coreModules = module {
        single<HttpClient> {
            getKtorClient {
                Log.d("Ktor Log", it)
            }
        }
        includes(MediaModule())
    }
    private val businessModules = module {
        includes(SampleModule())
        includes(HomeModule())
        includes(MusicModule())
    }


    fun setupKoinInjection(context: Context) {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(coreModules, businessModules)
        }
    }
}