plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin")
    kotlin("plugin.serialization")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(Libraries.Kotlin.KOTLIN_STDLIB)
    implementation(Libraries.Kotlin.COROUTINES_CORE)
    implementation(Libraries.Kotlin.SERIALIZATION_JSON)
}