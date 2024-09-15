plugins {
    alias(androidLibs.plugins.android.application)
    alias(kotlinLibs.plugins.jetbrains.kotlin.android)
    alias(kotlinLibs.plugins.jetbrains.compose.compiler)
    kotlin("plugin.serialization")
}

android {
    namespace = "com.example.trashArchitecture"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.trashArchitecture"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(thirdPartyLibs.koin.android.compose)
    implementation(project(":bridge"))

    implementation(androidLibs.androidx.core.ktx)
    implementation(androidLibs.androidx.lifecycle.runtime.ktx)
    implementation(androidLibs.androidx.lifecycle.viewmodel)
    implementation(androidLibs.androidx.lifecycle.viewmodel.savable)

    // UI
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


    implementation(kotlinLibs.kotlinx.serialization.json)
    implementation(thirdPartyLibs.retrofit.core)
    implementation(project(":feature:home:presentation"))
    implementation(project(":feature:sample:presentation"))

    testImplementation(androidLibs.junit)
    androidTestImplementation(androidLibs.androidx.junit)
    androidTestImplementation(androidLibs.androidx.espresso.core)
    androidTestImplementation(platform(androidLibs.androidx.compose.bom))
    androidTestImplementation(androidLibs.androidx.ui.test.junit4)

}