// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed

buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.7.4"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}

plugins {
    // Kotlin
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false

    // Hilt
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
}
true // Needed to make the Suppress annotation work for the plugins block