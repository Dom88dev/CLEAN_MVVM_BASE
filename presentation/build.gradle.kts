import Libraries.androidTestImpl
import Libraries.impl
import Libraries.kapt
import Libraries.testImpl

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    // google-service.json 추가 후 주석 해제
//    id("com.google.gms.google-services")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.dom.presentation"
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion
    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    viewBinding.isEnabled = true
}

dependencies {
    implementation(project(":domain"))

    implementation(platform(Libraries.Firebase.FB_PLATFORM))
    impl(Libraries.PresentationLibList.impl)
    kapt(Libraries.PresentationLibList.kapt)
    implementation(Libraries.ThirdParty.GLIDE_OKHTTP) {
        exclude("glide-parent")
    }

    impl(Libraries.CommonLibList.impl)
    kapt(Libraries.CommonLibList.kapt)
    testImpl(Libraries.CommonLibList.test)
    androidTestImpl(Libraries.CommonLibList.androidTest)
}