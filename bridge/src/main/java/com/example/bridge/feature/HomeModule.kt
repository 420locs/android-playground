package com.example.bridge.feature

import com.example.home.data.GoogleSampleMusicService
import com.example.home.data.HomeInternalRepository
import com.example.home.domain.HomeRepository
import com.example.home.domain.useCase.GetAllRemoteSongs
import com.example.home.presentation.main.HomeViewModel
import com.example.home.presentation.playingSong.PlayingSongViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal object HomeModule {
    internal operator fun invoke() = module {
        // Data Repositories
        singleOf(::GoogleSampleMusicService)
        single<HomeRepository> { HomeInternalRepository(get()) }

        // Use Cases
        factoryOf(::GetAllRemoteSongs)

        // ViewModels
        viewModelOf(::HomeViewModel)
        viewModelOf(::PlayingSongViewModel)
    }
}