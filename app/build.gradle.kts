@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)

    // Hilt
    id("com.google.dagger.hilt.android")

    // Symbol Processing: KSP (for Moshi, Room) and Kapt (for Hilt)
    id("com.google.devtools.ksp")

    // Safe Args
    id("androidx.navigation.safeargs")


    kotlin("kapt")
}

android {
    namespace = "ua.marchuk.photobuzz_dailyshapshot"
    compileSdk = 34

    defaultConfig {
        applicationId = "ua.marchuk.photobuzz_dailyshapshot"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    //Coil
    implementation(libs.coil)

    // Hilt
    implementation (libs.dagger.hilt.android)
    ksp (libs.hilt.android.compiler)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.okhttp)
    implementation (libs.converter.gson)    // Gson
    implementation (libs.gson) // Gson

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.legacy.support.v4)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)

    // Room
    implementation(libs.room)
    kapt (libs.androidx.room.compiler)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}