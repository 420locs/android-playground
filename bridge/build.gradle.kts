plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}


dependencies {
    implementation(libs.koin.core)
    implementation(project(":core:data:network"))
    implementation(project(":feature:sample:data"))
    implementation(project(":feature:sample:domain"))
}