plugins {
    alias(kotlinLibs.plugins.jetbrains.kotlin.jvm)
    kotlin("plugin.serialization")
}

dependencies {
    implementation(thirdPartyLibs.ktor.core)
    implementation(thirdPartyLibs.koin.core)
    implementation(kotlinLibs.kotlinx.serialization.json)
    implementation(project(":core:data:network"))
    implementation(project(":feature:home:domain"))
}
