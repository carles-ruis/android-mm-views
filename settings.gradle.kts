pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

include("app", "hyrule", "common", "settings")
rootProject.buildFileName = "build.gradle.kts"

project(":settings").projectDir = File(rootDir, "features/settings")
project(":hyrule").projectDir = File(rootDir, "features/hyrule")
