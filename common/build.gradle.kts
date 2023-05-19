@file:Suppress("UnstableApiUsage")

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.detekt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.navigation.safeargs)
}

android {
    namespace = "com.carles.common"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("main").java.srcDirs(File("$buildDir/generated/source/kapt/main"))
        getByName("test").java.srcDirs("src/test/kotlin")
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }

    kotlinOptions {
        jvmTarget = libs.versions.jvm.get()
    }
    buildFeatures {
        viewBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

detekt {
    config = files("$rootDir/default-detekt-config.yml")
}

dependencies {
    implementation(libs.kotlin)
    implementation(libs.material)
    implementation(libs.appcompat)
    implementation(libs.recyclerview)
    implementation(libs.constraintlayout)
    implementation(libs.preference)
    implementation(libs.bundles.navigation)
    implementation(libs.fragment)
    implementation(libs.splashscreen)
    implementation(libs.material)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    implementation(libs.bundles.retrofit)
    implementation(libs.room)
    implementation(libs.room.rxjava)
    kapt(libs.room.compiler)
    implementation(libs.bundles.lifecycle)
    kapt(libs.lifecycle.compiler)

    detektPlugins(libs.detekt)
    debugImplementation(libs.stetho.okhttp3)
    debugImplementation(libs.logginginterceptor)
    debugImplementation(libs.chucker)
    testImplementation(libs.bundles.test)
}
