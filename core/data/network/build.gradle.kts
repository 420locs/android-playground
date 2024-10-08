plugins {
    alias(kotlinLibs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(kotlinLibs.kotlinx.serialization.json)
    implementation(thirdPartyLibs.retrofit.core)
    implementation(thirdPartyLibs.okhttp.logging)
    implementation(thirdPartyLibs.retrofit.kotlin.serialization)

    // Ktor Client
    implementation(thirdPartyLibs.ktor.core)
    implementation(thirdPartyLibs.ktor.android)
    implementation(thirdPartyLibs.ktor.logging.jvm)
    implementation(thirdPartyLibs.ktor.client.negotiation)
    implementation(thirdPartyLibs.ktor.serialization)
}