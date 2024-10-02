plugins {
    alias(androidLibs.plugins.android.library)
    alias(kotlinLibs.plugins.jetbrains.kotlin.android)
    kotlin("plugin.serialization")
}

android {
    namespace = "com.example.trashArchitecture.media"
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
    // koin
    implementation(thirdPartyLibs.koin.android)
    // media3
    implementation(androidLibs.androidx.media3.session)
    implementation(androidLibs.androidx.media3.exoplayer)
    implementation(androidLibs.androidx.media3.ui)
    implementation(androidLibs.androidx.media3.common)

    implementation(kotlinLibs.kotlinx.serialization.json)
    implementation(androidLibs.androidx.core.ktx)
}