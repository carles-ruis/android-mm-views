object TestDependence {
    private val androidTestRulesVersion = "1.5.0"
    private val androidTestRunnerVersion = "1.1.5"
    private val mockkVersion = "1.9.3"
    private val archCoreTestingVersion = "2.2.0-rc01"
    private val espressoVersion = "3.2.0"
    private val jUnitVersion = "4.13.2"

    private val jUnit = "junit:junit:$jUnitVersion"
    private val mockk = "io.mockk:mockk:$mockkVersion"
    private val archCoreTesting = "androidx.arch.core:core-testing:$archCoreTestingVersion"
    private val testRunner = "androidx.test.ext:junit:$androidTestRunnerVersion"
    private val testRules = "androidx.test:rules:$androidTestRulesVersion"
    private val espressoContrib = "com.android.support.test.espresso:espresso-contrib:$espressoVersion["
    private val espresso = "com.android.support.test.espresso:espresso-core:$espressoVersion["

    val hilt = "com.google.dagger:hilt-android-testing:${Version.hilt}"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"

    val testImplementations = listOf(jUnit, mockk, archCoreTesting)
    val androidTestImplementations = listOf(jUnit, archCoreTesting, testRunner, testRules, espressoContrib, espresso)
}