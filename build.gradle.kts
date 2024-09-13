// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(androidLibs.plugins.android.application) apply false
    alias(kotlinLibs.plugins.jetbrains.kotlin.android) apply false
    alias(kotlinLibs.plugins.jetbrains.kotlin.jvm) apply false
    alias(kotlinLibs.plugins.jetbrains.compose.compiler) apply false
    alias(androidLibs.plugins.google.devtools.ksp) apply false
    alias(androidLibs.plugins.android.library) apply false
    kotlin("plugin.serialization") version "2.0.20" apply false
}