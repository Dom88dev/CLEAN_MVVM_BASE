import org.gradle.api.artifacts.dsl.DependencyHandler

object Versions {
    const val KOTLIN_VERSION = "1.9.20"
    const val KOTLINX_COROUTINES = "1.7.0-Beta"
    const val KOTLINX_SERIALIZATION_JSON = "1.5.0"
    const val KOTLIN_COMPOSE_COMPILER = "1.4.4"
    const val COMPOSE_BOM = "2023.08.00"
    const val ACCOMPANIST = "0.30.1"
    const val COMPOSE_CONSTRAINT_LAYOUT = "1.0.1"
    const val COMPOSE_GLIDE = "1.0.0-alpha.3"

    const val HILT = "2.50"
    const val MATERIAL = "1.10.0"

    const val CORE_KTX = "1.8.0"
    const val APP_COMPAT = "1.6.1"
    const val ACTIVITY_KTX = "1.7.0"
    const val FRAGMENT_KTX = "1.5.6"
    const val NAVIGATION = "2.5.3"
    const val CONSTRAINT_LAYOUT = "2.2.0-alpha07"
    const val LIFECYCLE_KTX = "2.6.1"
    const val ROOM = "2.5.0"
    const val MULTIDEX = "2.0.1"
    const val CAMERA_X = "1.3.0-alpha04"
    const val CRYPTO = "1.1.0-alpha06"

    const val FIREBASE = "31.3.0"

    const val RETROFIT = "2.9.0"
    const val RETROFIT_SERIALIZATION = "0.8.0"
    const val OKHTTP = "4.10.0"

    const val GLIDE = "4.13.0"
    const val EXO_PLAYER = "2.18.1"

    const val MOSHI = "1.9.3"

    const val TIMBER = "5.0.1"

    const val REALM = "1.8.0"

    const val LOTTIE = "6.0.0"

    const val JUNIT = "4.13.2"
    const val ANDROID_JUNIT = "1.1.5"
    const val ESPRESSO_CORE = "3.5.1"
}

object PlugIns {
    const val ANDROID           = "android"
    const val JVM               = "jvm"
    const val SERIALIZATION     = "plugin.serialization"
    const val HILT              = "com.google.dagger.hilt.android"
    const val REALM             = "io.realm.kotlin"
    const val KSP               = "com.google.devtools.ksp"
}

object PluginsModule {
    const val KOTLIN_KAPT       = "kotlin-kapt"
    const val KSP               = "com.google.devtools.ksp"
    const val SERIALIZATION     = "plugin.serialization"
    const val HILT              = "dagger.hilt.android.plugin"
    const val REALM             = "io.realm.kotlin"
    const val CLOUD_MESSAGE     = "com.google.gms.google-services"
    const val CRASHLYTICS       = "com.google.firebase.crashlytics"
}

object Libraries {

    object Kotlin {
        const val KOTLIN_STDLIB      = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN_VERSION}"
        const val COROUTINES_CORE    = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINES}"
        const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLINX_COROUTINES}"
        const val SERIALIZATION_JSON = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLINX_SERIALIZATION_JSON}"
    }

    object Google {
        const val HILT_ANDROID          = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

        const val MATERIAL              = "com.google.android.material:material:${Versions.MATERIAL}"
    }

    object AndroidX {
        const val CORE_KTX                = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val APP_COMPAT              = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"

        const val ACTIVITY_KTX            = "androidx.activity:activity-ktx:${Versions.ACTIVITY_KTX}"
        const val FRAGMENT_KTX            = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"

        const val NAVIGATION_FRAGMENT     = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
        const val NAVIGATION_UI           = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"

        const val CONSTRAINT_LAYOUT       = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"

        const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_KTX}"
        const val LIFECYCLE_LIVEDATA_KTX  = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_KTX}"

        const val ROOM_RUNTIME            = "androidx.room:room-runtime:${Versions.ROOM}"
        const val ROOM_KTX                = "androidx.room:room-ktx:${Versions.ROOM}"
        const val ROOM_COMPILER           = "androidx.room:room-compiler:${Versions.ROOM}"

        const val MULTIDEX                = "androidx.multidex:multidex:${Versions.MULTIDEX}"

        const val CAMERA_X_CORE           = "androidx.camera:camera-core:${Versions.CAMERA_X}"
        const val CAMERA_X_CAM2           = "androidx.camera:camera-camera2:${Versions.CAMERA_X}"
        const val CAMERA_X_LIFECYCLE      = "androidx.camera:camera-lifecycle:${Versions.CAMERA_X}"
        const val CAMERA_X_VIEW           = "androidx.camera:camera-view:${Versions.CAMERA_X}"

        //키를 안전하게 관리하고 파일 및 sharedpreference를 암호화
        const val CRYPTO                  = "androidx.security:security-crypto-ktx:${Versions.CRYPTO}"

        val BottomNavigation = listOf(NAVIGATION_UI, NAVIGATION_FRAGMENT)
        val Room             = listOf(ROOM_RUNTIME, ROOM_KTX)
    }

    object Compose {
        const val BOM               = "androidx.compose:compose-bom:${Versions.COMPOSE_BOM}"
        const val MATERIAL          = "androidx.compose.material3:material3"
        const val FOUNDATION        = "androidx.compose.foundation:foundation"
        const val FOUND_LAYOUT      = "androidx.compose.foundation:foundation-layout"
        const val LAYOUT            = "androidx.compose.foundation:foundation-layout"
        const val RUNTIME           = "androidx.compose.runtime:runtime"
        const val UI                = "androidx.compose.ui:ui"
        const val UI_TOOL           = "androidx.compose.ui:ui-tooling"   // debugImplementation
        const val PREVIEW           = "androidx.compose.ui:ui-tooling-preview"
        const val VIEW_BINDING      = "androidx.compose.ui:ui-viewbinding"

        const val PERMISSIONS       = "com.google.accompanist:accompanist-permissions:${Versions.ACCOMPANIST}"
        const val SYSTEM_UI         = "com.google.accompanist:accompanist-systemuicontroller:${Versions.ACCOMPANIST}"
        const val WEB               = "com.google.accompanist:accompanist-webview:${Versions.ACCOMPANIST}"

        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout-compose:${Versions.COMPOSE_CONSTRAINT_LAYOUT}"
        const val ACTIVITY          = "androidx.activity:activity-compose:${Versions.ACTIVITY_KTX}"
        const val VIEW_MODEL        = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.LIFECYCLE_KTX}"

        const val GLIDE = "com.github.bumptech.glide:compose:${Versions.COMPOSE_GLIDE}"

        val composeLibs = arrayListOf(
            MATERIAL,
            FOUNDATION,
            FOUND_LAYOUT,
            LAYOUT,
            RUNTIME,
            UI,
            PERMISSIONS,
            SYSTEM_UI,
            WEB,
            VIEW_BINDING,
            PREVIEW,
            ACTIVITY,
            VIEW_MODEL,
            CONSTRAINT_LAYOUT,
            VIEW_BINDING,
            GLIDE
        )
    }

    object Firebase {
        const val FB_PLATFORM               = "com.google.firebase:firebase-bom:${Versions.FIREBASE}"
        const val CRASHLYTICS               = "com.google.firebase:firebase-crashlytics-ktx"
        const val ANALYTICS                 = "com.google.firebase:firebase-analytics-ktx"
        const val MESSAGING                 = "com.google.firebase:firebase-messaging-ktx"
    }

    object ThirdParty {
        const val RETROFIT                          = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val RETROFIT_CONVERTER_GSON           = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
        const val RETROFIT_CONVERTER_SERIALIZATION  = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.RETROFIT_SERIALIZATION}"
        const val OKHTTP                            = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
        const val OKHTTP_LOGGING_INTERCEPTOR        = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
        const val GLIDE_OKHTTP                      = "com.github.bumptech.glide:okhttp3-integration:${Versions.OKHTTP}"
        const val GLIDE                             = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
        const val GLIDE_COMPILER                    = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
        const val EXO_PLAYER                        = "com.google.android.exoplayer:exoplayer:${Versions.EXO_PLAYER}"
        const val MOSHI                             = "com.squareup.moshi:moshi:${Versions.MOSHI}"
        const val MOSHI_CODEGEN                     = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI}"
        // 로그 라이브러리
        const val TIMBER                            = "com.jakewharton.timber:timber:${Versions.TIMBER}"

        const val REALM_BASE                        = "io.realm.kotlin:library-base:${Versions.REALM}"
        const val REALM_SYNC                        = "io.realm.kotlin:library-sync:${Versions.REALM}"

        const val LOTTIE                            = "com.airbnb.android:lottie:${Versions.LOTTIE}"
        // kakao link
        const val KAKAO_LINK                        = "com.kakao.sdk:v2-share:2.11.0"
        // PDF 뷰어
        const val PDF_VIEWER                        = "com.github.mhiew:android-pdf-viewer:3.2.0-beta.3"

        const val NAVER_MAP                         = "com.naver.maps:map-sdk:3.17.0"

        val RetroFitGroup = listOf(RETROFIT, RETROFIT_CONVERTER_GSON, OKHTTP, OKHTTP_LOGGING_INTERCEPTOR)
        val Realm = listOf(REALM_BASE, REALM_SYNC)
    }

    object UnitTest {
        const val JUNIT         = "junit:junit:${Versions.JUNIT}"
    }

    object AndroidTest {
        const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    }

    // android 모듈에서 공통적으로 받는 라이브러리 리스트
    object CommonLibList {
        val impl = listOf<String>(
            Kotlin.KOTLIN_STDLIB, Kotlin.COROUTINES_CORE, Kotlin.COROUTINES_ANDROID,
            AndroidX.CORE_KTX, AndroidX.APP_COMPAT, Google.MATERIAL, Google.HILT_ANDROID,
            ThirdParty.TIMBER, Kotlin.SERIALIZATION_JSON
        )

        val kapt = listOf<String>(
            Google.HILT_ANDROID_COMPILER
        )

        val test = listOf<String>(
            UnitTest.JUNIT
        )

        val androidTest =  listOf<String>(
            AndroidTest.ANDROID_JUNIT, AndroidTest.ESPRESSO_CORE
        )
    }

    /**
     * app단에서 사용되는 라이브러리
     */
    object AppLibList {
        val impl = listOf<String>(
            AndroidX.MULTIDEX
        )
    }

    /**
     * data단에서 사용되는 라이브러리
     */
    object DataLibList {
        val impl = arrayListOf<String>().also {
            it.addAll(ThirdParty.RetroFitGroup)
            it.addAll(AndroidX.Room)
//            it.addAll(ThirdParty.Realm)
        }

        val kapt = arrayListOf<String>().also {
            it.add(AndroidX.ROOM_COMPILER)
        }
    }

    /**
     * presentation단에서 사용되는 라이브러리
     */
    object PresentationLibList {
        val impl = arrayListOf<String>(
            AndroidX.ACTIVITY_KTX, AndroidX.FRAGMENT_KTX,
            AndroidX.CONSTRAINT_LAYOUT, AndroidX.LIFECYCLE_VIEWMODEL_KTX,
            Firebase.MESSAGING, Firebase.ANALYTICS, Firebase.CRASHLYTICS,
            ThirdParty.GLIDE
        ).also {
            it.addAll(AndroidX.BottomNavigation)
            it.add(ThirdParty.PDF_VIEWER)
            it.add(AndroidX.CRYPTO)
        }

        val kapt = listOf<String>(
            ThirdParty.GLIDE_COMPILER
        )
    }

    fun DependencyHandler.ksp(list: List<String>) {
        list.forEach { dpc ->
            add("ksp", dpc)
        }
    }

    fun DependencyHandler.impl(list: List<String>) {
        list.forEach { dpc ->
            add("implementation", dpc)
        }
    }

    fun DependencyHandler.androidTestImpl(list: List<String>) {
        list.forEach { dpc ->
            add("androidTestImplementation", dpc)
        }
    }

    fun DependencyHandler.testImpl(list: List<String>) {
        list.forEach { dpc ->
            add("testImplementation", dpc)
        }
    }
}