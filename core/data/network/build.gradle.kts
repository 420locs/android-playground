plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(dataSourceLibs.retrofit.core)
    implementation(dataSourceLibs.okhttp.logging)
    implementation(dataSourceLibs.retrofit.kotlin.serialization)
}