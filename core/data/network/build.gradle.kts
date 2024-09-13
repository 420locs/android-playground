plugins {
    alias(kotlinLibs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(kotlinLibs.kotlinx.serialization.json)
    implementation(thirdPartyLibs.retrofit.core)
    implementation(thirdPartyLibs.okhttp.logging)
    implementation(thirdPartyLibs.retrofit.kotlin.serialization)
}