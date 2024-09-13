package com.example.sample.presentation.main

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object SamplePresentationModule {
    val viewModelModules = module {
        viewModelOf(::SampleViewModel)
    }
}