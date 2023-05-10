package com.carles.mm.matchers

import android.view.View
import android.widget.ImageView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object CustomDrawableMatchers {

    fun hasDrawable(): Matcher<View> = HasDrawableMatcher()
}

private class HasDrawableMatcher : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description) {
        description.appendText("has drawable")
    }

    override fun matchesSafely(target: View): Boolean {
        return (target as? ImageView)?.drawable != null
    }
}