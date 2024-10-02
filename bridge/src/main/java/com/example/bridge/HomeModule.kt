package com.example.bridge

import com.example.bridge.core.Module
import com.example.home.data.GoogleSampleMusicApi
import com.example.home.data.GoogleSampleMusicService
import com.example.home.data.HomeInternalRepository
import com.example.home.domain.HomeRepository
import com.example.home.domain.useCase.GetAllRemoteSongs
import com.example.network.NetworkApi
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal object HomeModule : Module() {
    override val dataModules = module {
        single<GoogleSampleMusicService> {
            GoogleSampleMusicService(get())
        }
        factory<HomeRepository> {
            HomeInternalRepository(get())
        }
    }
    override val useCaseModules = module {
        single<GetAllRemoteSongs> { GetAllRemoteSongs(get()) }
    }
}