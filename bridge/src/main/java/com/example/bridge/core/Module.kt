package com.example.bridge.core

import org.koin.core.module.Module

internal abstract class Module {
    protected abstract val dataModules: Module

    protected abstract val useCaseModules: Module

    internal fun getAllBusinessModules() = listOf(
        dataModules,
        useCaseModules,
    )
}