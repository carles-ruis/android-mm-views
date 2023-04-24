@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("io.gitlab.arturbosch.detekt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.carles.hyrule"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        javaCompileOptions {
            annotationProcessorOptions {
                arguments.put("room.schemaLocation", "$projectDir/schemas")
            }
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
    kapt {
        correctErrorTypes = true
    }
}

detekt {
    config = files("$rootDir/default-detekt-config.yml")
}

dependencies {
    implementation(project(":common"))
    implementation(Dependence.kotlin)
    implementation(Dependence.material)
    implementation(Dependence.appCompat)
    implementation(Dependence.recyclerview)
    implementation(Dependence.constraintLayout)
    implementation(Dependence.preference)
    implementation(Dependence.navigation)
    implementation(Dependence.navigationFragment)
    implementation(Dependence.fragment)
    implementation(Dependence.hilt)
    kapt(Dependence.hiltCompiler)
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
    implementation(Dependence.glide)

    detektPlugins(Dependence.detekt)

    Dependence.testImplementations.forEach(::testImplementation)
}