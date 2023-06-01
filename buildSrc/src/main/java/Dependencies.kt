import org.gradle.api.artifacts.dsl.DependencyHandler

object Versions {
    const val KOTLIN_VERSION = "1.8.10"
    const val KOTLINX_COROUTINES = "1.7.0-Beta"
    const val KOTLINX_SERIALIZATION_JSON = "1.5.0"
    const val BUILD_GRADLE = "4.2.1"

    const val HILT = "2.45"
    const val MATERIAL = "1.8.0"

    const val CORE_KTX = "1.8.0"
    const val APP_COMPAT = "1.6.1"
    const val ACTIVITY_KTX = "1.7.0"
    const val FRAGMENT_KTX = "1.5.6"
    const val NAVIGATION = "2.5.3"
    const val CONSTRAINT_LAYOUT = "2.1.4"
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
    const val ANDROID_JUNIT = "1.1.2"
    const val ESPRESSO_CORE = "3.3.0"
}

object PlugIns {
    const val ANDROID           = "android"
    const val JVM               = "jvm"
    const val SERIALIZATION     = "plugin.serialization"
    const val HILT              = "com.google.dagger.hilt.android"
    const val REALM             = "io.realm.kotlin"
}

object PluginsModule {
    const val KOTLIN_KAPT       = "kotlin-kapt"
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

        const val CRYPTO                  = "androidx.security:security-crypto-ktx:${Versions.CRYPTO}"

        val BottomNavigation = listOf(NAVIGATION_UI, NAVIGATION_FRAGMENT)
        val Room             = listOf(ROOM_RUNTIME, ROOM_KTX)
        val CameraGroup      = listOf(CAMERA_X_CORE, CAMERA_X_CAM2, CAMERA_X_LIFECYCLE, CAMERA_X_VIEW)
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
        val impl = arrayListOf<String>(
            Firebase.MESSAGING, Firebase.ANALYTICS, Firebase.CRASHLYTICS,
            AndroidX.MULTIDEX
        ).also {
            it.addAll(AndroidX.CameraGroup)
        }
    }

    /**
     * data단에서 사용되는 라이브러리
     */
    object DataLibList {
        val impl = arrayListOf<String>().also {
            it.addAll(ThirdParty.RetroFitGroup)
            it.addAll(AndroidX.Room)
            it.addAll(ThirdParty.Realm)
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
            Firebase.MESSAGING, ThirdParty.GLIDE, ThirdParty.LOTTIE
        ).also {
            it.addAll(AndroidX.CameraGroup)
        }

        val kapt = listOf<String>(
            ThirdParty.GLIDE_COMPILER
        )
    }

    fun DependencyHandler.kapt(list: List<String>) {
        list.forEach { dpc ->
            add("kapt", dpc)
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