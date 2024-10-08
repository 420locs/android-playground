plugins {
    alias(androidLibs.plugins.android.library)
    alias(kotlinLibs.plugins.jetbrains.kotlin.android)
    alias(kotlinLibs.plugins.jetbrains.compose.compiler)
    kotlin("plugin.serialization")
}

android {
    namespace = "com.example.trashArchitecture.sample"
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
    implementation(project(":feature:sample:domain"))
    implementation(project(":core:push-notification"))

    implementation(thirdPartyLibs.koin.android.compose)

    implementation(kotlinLibs.kotlinx.serialization.json)

    implementation(androidLibs.androidx.core.ktx)
    implementation(androidLibs.androidx.lifecycle.runtime.ktx)
    implementation(androidLibs.androidx.lifecycle.viewmodel)
    implementation(androidLibs.androidx.lifecycle.viewmodel.savable)

    // Compose UI
    implementation(androidLibs.androidx.activity.compose)
    implementation(platform(androidLibs.androidx.compose.bom))
    implementation(androidLibs.androidx.ui)
    implementation(androidLibs.androidx.ui.graphics)
    implementation(androidLibs.androidx.ui.tooling.preview)
    implementation(androidLibs.androidx.material3)
    implementation(androidLibs.androidx.compose.navigation)
    debugImplementation(androidLibs.androidx.ui.tooling)
    debugImplementation(androidLibs.androidx.ui.test.manifest)
    implementation(project(":core:design-system"))
}