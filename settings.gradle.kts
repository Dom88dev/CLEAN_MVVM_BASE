pluginManagement {
    buildscript {
        repositories {
            google()
            mavenCentral()
            gradlePluginPortal()
            maven {
                url = uri("https://storage.googleapis.com/r8-releases/raw")
            }
        }
        dependencies {
            classpath("com.android.tools.build:gradle:8.1.3")
            //            hilt 에서 javapoet 관련 not found method 관련 대응..
            classpath("com.squareup:javapoet:1.13.0")
            classpath("com.google.gms:google-services:4.4.0")
            classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "CLEAN_MVVM_BASE"
include(":app", ":presentation", ":data", ":domain")
