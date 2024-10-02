package com.example.bridge

import com.example.bridge.core.coreDataModules
import com.example.media.MediaModule
import com.example.music.presentation.MusicModule

val coreModules = listOf(
    coreDataModules,
    MediaModule()
)
val businessModules = SampleModule.getAllBusinessModules() +
        HomeModule.getAllBusinessModules() +
        MusicModule()
