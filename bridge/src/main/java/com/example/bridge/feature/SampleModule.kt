package com.example.bridge.feature

import com.example.sample.data.trash.PlaceHolderService
import com.example.sample.data.trash.SampleInternalRepository
import com.example.sample.domain.usecase.GetListPost
import com.example.sample.presentation.main.SampleViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal object SampleModule {
    internal operator fun invoke() = module {
        // Data Repositories
        singleOf(::PlaceHolderService)
        singleOf(::SampleInternalRepository)

        // Use Cases
        factoryOf(::GetListPost)

        // ViewModels
        viewModelOf(::SampleViewModel)
    }
}