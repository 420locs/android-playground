plugins {
    alias(kotlinLibs.plugins.jetbrains.kotlin.jvm)
}


dependencies {
    implementation(thirdPartyLibs.koin.core)
    implementation(project(":core:data:network"))
    implementation(project(":feature:sample:data"))
    implementation(project(":feature:sample:domain"))
}