package com.example.trashArchitecture

import com.example.bridge.businessModules
import com.example.bridge.coreModules
import com.example.sample.presentation.main.SamplePresentationModule

internal object KoinConfiguration {
    internal val modules = coreModules + businessModules + getFeatureViewModelModules()
    private fun getFeatureViewModelModules() = listOf(
        SamplePresentationModule.viewModelModules,
    )
}