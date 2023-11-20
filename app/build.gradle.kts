import Libraries.androidTestImpl
import Libraries.impl
import Libraries.kapt
import Libraries.testImpl

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id(PluginsModule.KOTLIN_KAPT)
    kotlin(PluginsModule.SERIALIZATION)
    id(PluginsModule.HILT)
    // google-service.json 추가 후 주석 해제
//    id(PluginsModule.CLOUD_MESSAGE)
    id(PluginsModule.CRASHLYTICS)
}

android {
    namespace = AppConfig.applicationId
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFile(getDefaultProguardFile("proguard-android.txt"))
            val files = rootProject.file("proguard").listFiles()?.filter { it.name.startsWith("proguard") }?.toTypedArray() ?: toTypedArray()
            proguardFiles(*files)
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            proguardFile(getDefaultProguardFile("proguard-android.txt"))
            val files = rootProject.file("proguard").listFiles()?.filter { it.name.startsWith("proguard") }?.toTypedArray() ?: toTypedArray()
            proguardFiles(*files)
        }
    }
    flavorDimensions += "server"
    productFlavors {
        create("local") {
            dimension = "server"
            buildConfigField("String", "BASE_DOMAIN_URL", "\"http://19.19.20.139:8080\"")
            buildConfigField("String", "SECRET", "\"secret\"")
        }
        create("staging") {
            dimension = "server"
            buildConfigField("String", "BASE_DOMAIN_URL", "\"http://112.217.204.155:8080\"")
            buildConfigField("String", "SECRET", "\"secret\"")
        }
        create("live") {
            dimension = "server"
            buildConfigField("String", "BASE_DOMAIN_URL", "\"https://gw.aiccsharing.com/\"")
            buildConfigField("String", "SECRET", "\"vpLhklaBDL\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    viewBinding.isEnabled = true

    packaging {
        resources {
            excludes += "/META-INF/DEPENDENCIES"
            excludes += "/META-INF/LICENSE"
            excludes += "/META-INF/LICENSE.txt"
            excludes += "/META-INF/license.txt"
            excludes += "/META-INF/NOTICE"
            excludes += "/META-INF/NOTICE.txt"
            excludes += "/META-INF/notice.txt"
            excludes += "/META-INF/ASL2.0"
            excludes += "/META-INF/*.kotlin_module"
            excludes += "/META-INF/versions/9/previous-compilation-data.bin"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    implementation(platform(Libraries.Firebase.FB_PLATFORM))
    impl(Libraries.AppLibList.impl)
    impl(Libraries.DataLibList.impl)
    kapt(Libraries.DataLibList.kapt)

    impl(Libraries.CommonLibList.impl)
    kapt(Libraries.CommonLibList.kapt)
    testImpl(Libraries.CommonLibList.test)
    androidTestImpl(Libraries.CommonLibList.androidTest)
}