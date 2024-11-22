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
            classpath("com.android.tools.build:gradle:8.7.2")
            //            hilt 에서 javapoet 관련 not found method 관련 대응..
            classpath("com.squareup:javapoet:1.13.0")
            classpath("com.google.gms:google-services:4.4.2")
            classpath("com.google.firebase:firebase-crashlytics-gradle:3.0.2")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven ("https://jitpack.io" )
    }
}
rootProject.name = "CLEAN_MVVM_BASE"
include(":app", ":presentation", ":data", ":domain")
