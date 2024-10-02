plugins {
    alias(kotlinLibs.plugins.jetbrains.kotlin.jvm)
    kotlin("plugin.serialization")
}

dependencies {
    implementation(kotlinLibs.kotlinx.serialization.json)
}