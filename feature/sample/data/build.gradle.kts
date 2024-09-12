plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    kotlin("plugin.serialization")
}

dependencies {
    implementation(libs.koin.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(dataSourceLibs.retrofit.core)
    implementation(project(":core:data:network"))
    implementation(project(":feature:sample:domain"))
}

