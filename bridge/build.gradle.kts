plugins {
    alias(androidLibs.plugins.android.library)
    alias(kotlinLibs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.trashArchitecture.bridge"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Provide interface of Player
    implementation(androidLibs.androidx.media3.exoplayer)
    // Provide interface of HttpClient
    implementation(thirdPartyLibs.ktor.core)
    implementation(thirdPartyLibs.koin.android.compose)
    implementation(project(":core:data:network"))
    implementation(project(":core:media"))
    implementation(project(":feature:music:presentation"))
    implementation(project(":feature:sample:domain"))
    implementation(project(":feature:sample:data"))
    implementation(project(":feature:sample:presentation"))
    implementation(project(":feature:home:domain"))
    implementation(project(":feature:home:data"))
    implementation(project(":feature:home:presentation"))
}
