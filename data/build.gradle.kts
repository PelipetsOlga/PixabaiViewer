plugins {
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    implementation(libs.retrofit.library)
    implementation(libs.retrofit.converter.gson.library)
    implementation(libs.okhttp.logging.interceptor.library)
    implementation(libs.okhttp.library)
    implementation (libs.androidx.paging.runtime)
    implementation (libs.androidx.paging.compose)

    testImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.junitExt)
    androidTestImplementation(libs.androidx.espresso)
}