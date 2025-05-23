import Libraries.androidTestImpl
import Libraries.impl
import Libraries.ksp
import Libraries.testImpl

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(PluginsModule.KSP)
    id(PluginsModule.HILT)
    kotlin(PluginsModule.SERIALIZATION)
//    id(PluginsModule.REALM)
}

android {
    namespace = "${AppConfig.baseNameSpace}data"
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
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    implementation(project(":domain"))

    impl(Libraries.DataLibList.impl)
    ksp(Libraries.DataLibList.ksp)

    impl(Libraries.CommonLibList.impl)
    ksp(Libraries.CommonLibList.kapt)
    testImpl(Libraries.CommonLibList.test)
    androidTestImpl(Libraries.CommonLibList.androidTest)
}