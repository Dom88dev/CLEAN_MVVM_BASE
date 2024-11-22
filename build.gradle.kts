// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    kotlin(PlugIns.ANDROID) version Versions.KOTLIN_VERSION apply false
    kotlin(PlugIns.JVM) version Versions.KOTLIN_VERSION apply false
    kotlin(PlugIns.SERIALIZATION) version Versions.KOTLIN_VERSION
    id(PlugIns.HILT) version Versions.HILT apply false
    id(PlugIns.KSP) version "1.9.20-1.0.14" apply false
//    id(PlugIns.REALM) version Versions.REALM apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

// BuildConfig Field
subprojects {
    afterEvaluate {
        (extensions.findByName("android") as? com.android.build.gradle.BaseExtension)?.apply {
            defaultConfig {
                buildConfigField("String", "APPLICATION_ID", "\"${AppConfig.applicationId}\"")
                buildConfigField("String", "VERSION_NAME", "\"${AppConfig.versionName}\"")
            }
        }
    }
}