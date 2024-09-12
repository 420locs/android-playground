package com.example.bridge

import com.example.bridge.core.Module
import com.example.sample.data.trash.SampleInternalRepository
import com.example.sample.domain.SampleRepository
import com.example.sample.domain.usecase.GetListPost
import org.koin.dsl.module

internal object SampleModule : Module() {
    public override val dataModules = module {
        factory<SampleRepository> {
            SampleInternalRepository(get())
        }
    }

    public override val useCaseModules = module {
        factory { GetListPost(get()) }
    }
}