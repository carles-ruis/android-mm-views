package com.carles.mm.features

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.carles.mm.MainActivity
import com.carles.mm.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class SettingsTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun displayAndSetSettings() {
        // settings screen
        onView(withId(R.id.settings_graph)).perform(click())
        onView(withId(R.id.main_toolbar)).check(matches(hasDescendant(withText(R.string.settings))))
        onView(withText(R.string.preferences_cache_expiration))
            .check(matches(isDisplayed()))
            .perform(click())

        // cache expiration dialog
        val cacheEntries =
            InstrumentationRegistry.getInstrumentation().targetContext.resources.getStringArray(R.array.preferences_cache_entries)
        for (entry in cacheEntries) {
            onView(withText(entry)).check(matches(isDisplayed()))
        }
        onView(withText(R.string.preferences_cache_dont_use)).perform(click())

        // settings screen > cache expiration > back to settings string
        onView(withText(R.string.preferences_cache_dont_use)).check(matches(isDisplayed()))
        onView(withText(R.string.preferences_cache_expiration)).perform(click())
        onView(withText(android.R.string.cancel)).perform(click())
        onView(withText(R.string.preferences_cache_dont_use)).check(matches(isDisplayed()))
        onView(withText(R.string.preferences_cache_expiration)).perform(click())
        onView(withText(R.string.preferences_cache_ten_minutes)).perform(click())
        onView(withText(R.string.preferences_cache_ten_minutes)).check(matches(isDisplayed()))
    }
}