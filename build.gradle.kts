// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    kotlin("android") version "1.8.10" apply false
    kotlin("jvm") version "1.8.10" apply false
    kotlin("plugin.serialization") version "1.8.10"
    id("com.google.dagger.hilt.android") version "2.45" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}