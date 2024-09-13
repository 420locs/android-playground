plugins {
    alias(androidLibs.plugins.android.library)
    alias(kotlinLibs.plugins.jetbrains.kotlin.android)
    alias(kotlinLibs.plugins.jetbrains.compose.compiler)
    kotlin("plugin.serialization")
}

android {
    namespace = "com.example.trashArchitecture.home"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
//    implementation(project(":feature:home:domain"))

    implementation(thirdPartyLibs.koin.android.compose)

    implementation(kotlinLibs.kotlinx.serialization.json)

    implementation(androidLibs.androidx.core.ktx)
    implementation(androidLibs.androidx.lifecycle.runtime.ktx)
    implementation(androidLibs.androidx.lifecycle.viewmodel)
    implementation(androidLibs.androidx.lifecycle.viewmodel.savable)

    implementation(androidLibs.androidx.activity.compose)
    implementation(platform(androidLibs.androidx.compose.bom))
    implementation(androidLibs.androidx.ui)
    implementation(androidLibs.androidx.ui.graphics)
    implementation(androidLibs.androidx.ui.tooling.preview)
    implementation(androidLibs.androidx.material3)
    implementation(androidLibs.androidx.compose.navigation)
    debugImplementation(androidLibs.androidx.ui.tooling)
    debugImplementation(androidLibs.androidx.ui.test.manifest)
}