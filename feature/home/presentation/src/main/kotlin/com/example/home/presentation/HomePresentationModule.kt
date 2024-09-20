package com.example.home.presentation

import com.example.home.presentation.main.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object HomePresentationModule {
    val viewModelModules = module {
        viewModel {
            HomeViewModel()
        }
    }
}