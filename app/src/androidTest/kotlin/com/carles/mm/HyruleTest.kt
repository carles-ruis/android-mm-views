package com.carles.mm

import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.carles.mm.matchers.CustomDrawableMatchers
import com.carles.mm.matchers.CustomRecyclerViewMatchers.atPosition
import com.carles.mm.matchers.CustomRecyclerViewMatchers.recyclerViewSize
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.startsWith
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HyruleTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun displayMonstersAndNavigateToMonsterDetail() {
        val appName = InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.appname)
        // monsters screen
        onView(withId(R.id.main_toolbar))
            .check(matches(isDisplayed()))
            .check(matches(hasDescendant(withText(appName))))
        onView(withId(R.id.monsters_recycler))
            .check(matches(recyclerViewSize(16)))
            .check(matches(atPosition(2, withText("fire keese"))))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(15))
            .check(matches(atPosition(14, withText("stone talus (rare)"))))
            .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(withText("molduga"), click()))

        // monster detail screen
        onView(withId(R.id.main_toolbar)).check(matches(hasDescendant(withText("molduga"))))
        onView(withId(R.id.monster_locations)).check(matches(withText("Gerudo Desert")))
        onView(withId(R.id.monster_description)).check(matches(withText(startsWith("This massive monster swims"))))
        waitUntilTagged(R.string.tag_monster_image_loaded)
        onView(withId(R.id.monster_image)).check(matches(CustomDrawableMatchers.hasDrawable()))
        onView(withId(R.id.monster_image_url)).check(matches(not(isDisplayed())))
        onNavigateUpView().perform(click())

        // monster screen again
        onView(withId(R.id.main_toolbar)).check(matches(hasDescendant(withText(appName))))
        onView(withId(R.id.monsters_recycler))
            .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(withText("guardian scout iv"), click()))

        // monster detail again, back and exit
        onView(withId(R.id.main_toolbar)).check(matches(hasDescendant(withText("guardian scout iv"))))
        onView(withId(R.id.monster_locations)).check(matches(withText("")))
        waitUntilTagged(R.string.tag_monster_image_url)
        onView(withId(R.id.monster_image_url)).check(
            matches(withText("https://botw-compendium.herokuapp.com/api/v2/entry/guardian_scout_iv/imageerror"))
        )
        Espresso.pressBack()
        Espresso.pressBackUnconditionally()

        val isDestroyed = activityRule.scenario.state == Lifecycle.State.RESUMED
        val isCreated = activityRule.scenario.state == Lifecycle.State.CREATED
        assertTrue(isDestroyed || isCreated)
    }

    private fun waitUntilTagged(@StringRes tagId: Int) {
        val endTime = System.currentTimeMillis() + WAIT_TIMEOUT
        var displayed = false
        while (System.currentTimeMillis() < endTime && displayed.not()) {
            try {
                onView(withTagKey(tagId)).check(matches(isDisplayed()))
                displayed = true
            } catch (e: NoMatchingViewException) {
                // keep on trying
            }
        }
    }

    private fun onNavigateUpView(): ViewInteraction {
        return onView(allOf(instanceOf(AppCompatImageButton::class.java), withParent(withId(R.id.main_toolbar))))
    }

    companion object {
        private const val WAIT_TIMEOUT = 3_000L
    }
}