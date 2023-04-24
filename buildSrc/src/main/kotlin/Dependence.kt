object AppConfig {
    val minSdk = 23
    val compileSdk = 33
    val targetSdk = 33
    val versionCode = 1
    val versionName = "1.0"
    val testRunner = "com.carles.mm.CustomTestRunner"
}

object Version {
    val gradle = "7.4.2"
    val kotlin = "1.8.10"
    val benManes = "0.46.0"
    val detekt = "1.22.0"
    val hilt = "2.45"

    val appCompat = "1.7.0-alpha02"
    val core = "1.9.0"
    val material = "1.9.0-alpha02"
    val recyclerView = "1.3.0-rc01"
    val constraintLayout = "2.2.0-alpha07"
    val preference = "1.2.0"
    val navigation = "2.6.0-alpha05"
    val fragment = "1.6.0-alpha05"
    val splashScreen = "1.0.1"
    val retrofit = "2.9.0"
    val loggingInterceptor = "4.10.0"
    val rxJava = "2.2.21"
    val rxAndroid = "2.1.1"
    val lifecycle = "2.2.0"
    val room = "2.5.0"
    val koin = "3.3.3"
    val stetho = "1.6.0"
    val leakCanary = "2.10"
    val gson = "2.8.5"
    val glide = "4.14.2"
    val chucker = "3.5.2"

    val androidTestRules = "1.5.0"
    val androidTestRunner = "1.1.5"
    val mockk = "1.9.3"
    val archCoreTesting = "2.2.0-rc01"
    val espresso = "3.2.0"
    val jUnit = "4.13.2"
}

object Dependence {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    val core = "androidx.core:core:${Version.core}"
    val material = "com.google.android.material:material:${Version.material}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Version.recyclerView}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    val preference = "androidx.preference:preference-ktx:${Version.preference}"
    val navigation =  "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
    val fragment = "androidx.fragment:fragment-ktx:${Version.fragment}"
    val splashScreen = "androidx.core:core-splashscreen:${Version.splashScreen}"

    val rxJava = "io.reactivex.rxjava2:rxjava:${Version.rxJava}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Version.rxAndroid}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    val retrofitRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Version.retrofit}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.loggingInterceptor}"
    val roomRuntime = "androidx.room:room-runtime:${Version.room}"
    val roomCompiler = "androidx.room:room-compiler:${Version.room}"
    val roomRxJava = "androidx.room:room-rxjava2:${Version.room}"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Version.lifecycle}"
    val lifecycleReactive = "androidx.lifecycle:lifecycle-reactivestreams:${Version.lifecycle}"
    val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"
    val gson = "com.google.code.gson:gson:${Version.gson}"
    val glide = "com.github.bumptech.glide:glide:${Version.glide}"
    val hilt = "com.google.dagger:hilt-android:${Version.hilt}"
    val hiltCompiler = "com.google.dagger:hilt-compiler:${Version.hilt}"

    // Http inspector
    val chucker = "com.github.chuckerteam.chucker:library:${Version.chucker}"
    // Displays network traffic in chrome browser
    val stetho = "com.facebook.stetho:stetho:${Version.stetho}"
    val stethoOkHttp = "com.facebook.stetho:stetho-okhttp3:${Version.stetho}"
    // Memory leak detection library
    val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Version.leakCanary}"
    val detekt = "io.gitlab.arturbosch.detekt:detekt-formatting:${Version.detekt}"

    private val jUnit = "junit:junit:${Version.jUnit}"
    private val mockk = "io.mockk:mockk:${Version.mockk}"
    private val archCoreTesting = "androidx.arch.core:core-testing:${Version.archCoreTesting}"
    private val testRunner = "androidx.test.ext:junit:${Version.androidTestRunner}"
    private val testRules = "androidx.test:rules:${Version.androidTestRules}"
    private val espressoContrib = "com.android.support.test.espresso:espresso-contrib:${Version.espresso}"
    private val espresso = "com.android.support.test.espresso:espresso-core:${Version.espresso}"
    val hiltTest = "com.google.dagger:hilt-android-testing:${Version.hilt}"
    val hiltCompilerTest = "com.google.dagger:hilt-android-compiler:${Version.hilt}"

    val testImplementations = listOf(jUnit, mockk, archCoreTesting)
    val androidTestImplementations = listOf(jUnit, archCoreTesting, testRunner, testRules, espressoContrib, espresso)
}