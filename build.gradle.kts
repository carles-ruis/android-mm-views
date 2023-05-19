@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.kapt).apply(false)
    alias(libs.plugins.hilt).apply(false)

    // use: ./gradlew dependencyUpdates
    alias(libs.plugins.gradle.versions)
    // ./gradlew detekt
    alias(libs.plugins.detekt)
}

allprojects {
    repositories {
        google()
        gradlePluginPortal()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
