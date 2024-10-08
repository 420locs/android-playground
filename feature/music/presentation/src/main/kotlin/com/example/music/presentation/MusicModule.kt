package com.example.music.presentation

import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.example.music.presentation.main.MusicPlayerViewModel
import org.koin.dsl.module

object MusicModule {
    operator fun invoke() = module {
        viewModelOf(::MusicPlayerViewModel)
    }
}