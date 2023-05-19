@file:Suppress("UnstableApiUsage")

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.detekt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.navigation.safeargs)
}

android {
    namespace = "com.carles.mm"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.carles.carleskotlin"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.carles.mm.runner.CustomTestRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("main").java.srcDirs(File("$buildDir/generated/source/kapt/main"))
        getByName("test").java.srcDirs("src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
        getByName("androidTest").java.srcDirs(File("$buildDir/generated/source/kapt/main"))
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
        animationsDisabled = true
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
    implementation(project(":hyrule"))
    implementation(project(":settings"))
    implementation(project(":common"))
    implementation(libs.kotlin)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.bundles.navigation)
    implementation(libs.fragment)
    implementation(libs.splashscreen)
    implementation(libs.material)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.bundles.retrofit)
    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    implementation(libs.room)

    debugImplementation(libs.stetho)
    //debugImplementation(libs.leakCanary)
    detektPlugins(libs.detekt)
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.android.test)
    androidTestImplementation(libs.test.hilt)
    kaptAndroidTest(libs.test.hilt.compiler)
}
