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
    id(PluginsModule.REALM)
}

android {
    namespace = "com.dom.data"
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion
    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":domain"))

    impl(Libraries.DataLibList.impl)
    kapt(Libraries.DataLibList.kapt)

    impl(Libraries.CommonLibList.impl)
    kapt(Libraries.CommonLibList.kapt)
    testImpl(Libraries.CommonLibList.test)
    androidTestImpl(Libraries.CommonLibList.androidTest)
}