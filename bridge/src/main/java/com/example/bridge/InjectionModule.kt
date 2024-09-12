package com.example.bridge

import com.example.bridge.core.coreDataModules
import org.koin.dsl.module

val businessModules = coreDataModules +
        SampleModule.getAllBusinessModules()
