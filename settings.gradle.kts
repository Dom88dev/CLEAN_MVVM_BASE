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
            classpath("com.android.tools.build:gradle:7.4.2")
            //            hilt 에서 javapoet 관련 not found method 관련 대응..
            classpath("com.squareup:javapoet:1.13.0")
            classpath("com.google.gms:google-services:4.3.15")
            classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.4")
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
