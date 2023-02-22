@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("io.gitlab.arturbosch.detekt")
}

android {
    compileSdk = AppConfig.compileSdk
    namespace = "com.carles.core"

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        testInstrumentationRunner = AppConfig.testRunner
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
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

detekt {
    config = files("$rootDir/default-detekt-config.yml")
}

dependencies {
    implementation(Dependence.kotlin)
    implementation(Dependence.material)
    implementation(Dependence.appCompat)
    implementation(Dependence.recyclerview)
    implementation(Dependence.constraintLayout)
    implementation(Dependence.preference)
    implementation(Dependence.navigation)
    implementation(Dependence.navigationFragment)
    implementation(Dependence.fragment)

    implementation(Dependence.koin)
    implementation(Dependence.koinNavigation)
    //implementation(Dependencies.koinScope)
    //implementation(Dependencies.koinViewModel)
    implementation(Dependence.rxJava)
    implementation(Dependence.rxAndroid)
    implementation(Dependence.retrofit)
    implementation(Dependence.retrofitConverterGson)
    implementation(Dependence.retrofitRxJava)
    implementation(Dependence.roomRuntime)
    kapt(Dependence.roomCompiler)
    implementation(Dependence.roomRxJava)
    implementation(Dependence.lifecycleExtensions)
    kapt(Dependence.lifecycleCompiler)
    implementation(Dependence.lifecycleReactive)
    implementation(Dependence.lifecycleLiveData)

    detektPlugins(Dependence.detekt)
    implementation(Dependence.stetho)
    implementation(Dependence.stethoOkHttp)

    Dependence.testImplementations.forEach(::testImplementation)

    implementation(kotlin("stdlib-jdk7", version = Version.kotlin))
}
