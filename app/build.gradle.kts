plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}
android {
    namespace = "com.example.photosearchapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.photosearchapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
//        buildConfigField("String", "API_KEY", API_KEY)
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.constraintlayout.compose)
    // implementation("androidx.constraintlayout:constraintlayout-compose:1.1.1")

    // System ui controller
    implementation (libs.accompanist.systemuicontroller)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)

    // Coil
    implementation (libs.coil.compose)

    // Room
    implementation (libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    ksp(libs.compiler)
    implementation (libs.androidx.room.paging)

    // Dagger - Hilt
    implementation (libs.hilt.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.material)
    implementation(libs.volley)
    ksp(libs.hilt.android.compiler)

    implementation (libs.androidx.hilt.navigation.compose)

    // Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    // Coroutine Lifecycle Scopes
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation (platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.espresso.core)
}