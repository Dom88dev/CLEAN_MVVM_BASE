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
        // android 26 아래 버전에서 LocalDate 내의 일부 함수를 사용하기위해 desugaring 설정
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    viewBinding.isEnabled = true
    buildFeatures {
        viewBinding = true
        buildConfig = true
        //compose 설정
//        compose = true
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = Versions.KOTLIN_COMPOSE_COMPILER
//    }
}

dependencies {
    implementation(project(":domain"))

    // android 26 아래 버전에서 LocalDate 내의 일부 함수를 사용하기위해 desugaring 설정
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.3")

    implementation(platform(Libraries.Firebase.FB_PLATFORM))
    impl(Libraries.PresentationLibList.impl)
    ksp(Libraries.PresentationLibList.ksp)
    implementation(Libraries.ThirdParty.GLIDE_OKHTTP) {
        exclude("glide-parent")
    }

//    implementation(platform(Libraries.Compose.BOM))
//    impl(Libraries.Compose.composeLibs)

    impl(Libraries.CommonLibList.impl)
    ksp(Libraries.CommonLibList.kapt)
    testImpl(Libraries.CommonLibList.test)
    androidTestImpl(Libraries.CommonLibList.androidTest)
}