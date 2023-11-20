plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin")
    kotlin(PluginsModule.SERIALIZATION)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(Libraries.Kotlin.KOTLIN_STDLIB)
    implementation(Libraries.Kotlin.COROUTINES_CORE)
    implementation(Libraries.Kotlin.SERIALIZATION_JSON)
}