import Libraries.androidTestImpl
import Libraries.impl
import Libraries.kapt
import Libraries.testImpl

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(PluginsModule.KOTLIN_KAPT)
    id(PluginsModule.HILT)
    kotlin(PluginsModule.SERIALIZATION)
}

android {
    namespace = "${AppConfig.baseNameSpace}presentation"
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion
    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    viewBinding.isEnabled = true
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
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