package com.example.bridge

import com.example.bridge.core.coreDataModules
import com.example.music.presentation.MusicModule

val coreModules = listOf(
    coreDataModules
)
val businessModules = SampleModule.getAllBusinessModules() +
        MusicModule()
