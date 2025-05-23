import Libraries.androidTestImpl
import Libraries.impl
import Libraries.ksp
import Libraries.testImpl
import org.gradle.internal.extensions.stdlib.capitalized
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompileTool

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id(PluginsModule.KSP)
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
//        compose = true
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = Versions.KOTLIN_COMPOSE_COMPILER
//    }

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
        // android 26 아래 버전에서 LocalDate 내의 일부 함수를 사용하기위해 desugaring 설정
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }

    // Room AutoMigration 사용 시 schema 파일 저장 위치 지정
//    ksp {
//        arg("room.schemaLocation", "$projectDir/data/schemas/com.dom.data.local.AppDatabase")
//    }

    viewBinding.isEnabled = true

    androidComponents {
        onVariants(selector().all()) { variant ->
            afterEvaluate {
                // This is a workaround for https://issuetracker.google.com/301244513 which depends on internal
                // implementations of the android gradle plugin and the ksp gradle plugin which might change in the future
                // in an unpredictable way.
                project.tasks.getByName("ksp" + variant.name.capitalized() + "Kotlin") {
                    val buildConfigTask = project.tasks.getByName("generate" + variant.name.capitalized() + "BuildConfig")
                            as (com.android.build.gradle.tasks.GenerateBuildConfig)

                    (this as (AbstractKotlinCompileTool<*>)).setSource(buildConfigTask.sourceOutputDir)
                }
            }
        }
    }

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

    // android 26 아래 버전에서 LocalDate 내의 일부 함수를 사용하기위해 desugaring 설정
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.3")

    implementation(platform(Libraries.Firebase.FB_PLATFORM))
    impl(Libraries.AppLibList.impl)
    impl(Libraries.DataLibList.impl)
    ksp(Libraries.DataLibList.ksp)

//    implementation(platform(Libraries.Compose.BOM))
//    impl(Libraries.Compose.composeLibs)

    impl(Libraries.CommonLibList.impl)
    ksp(Libraries.CommonLibList.kapt)
    testImpl(Libraries.CommonLibList.test)
    androidTestImpl(Libraries.CommonLibList.androidTest)
}