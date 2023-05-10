package com.carles.mm.matchers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

object CustomRecyclerViewMatchers {

    fun viewAtPosition(position: Int, targetViewId: Int, itemMatcher: Matcher<View>): Matcher<View> =
        ViewAtPositionMatcher(position, targetViewId, itemMatcher)

    fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> = AtPositionMatcher(position, itemMatcher)

    fun recyclerViewSize(expectedCount: Int): Matcher<View> = RecyclerViewSizeMatcher(expectedCount)

}

// https://medium.com/mindorks/some-useful-custom-espresso-matchers-in-android-33f6b9ca2240
private class ViewAtPositionMatcher(val position: Int, val targetViewId: Int, val itemMatcher: Matcher<View>) :
    BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

    override fun describeTo(description: Description) {
        description.appendText("has view id $itemMatcher at position $position");
    }

    override fun matchesSafely(recyclerView: RecyclerView): Boolean {
        val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)!!
        val targetView = viewHolder.itemView.findViewById<View>(targetViewId)
        return itemMatcher.matches(targetView)
    }
}

// https://stackoverflow.com/questions/31394569/how-to-assert-inside-a-recyclerview-in-espresso
private class AtPositionMatcher(val position: Int, val itemMatcher: Matcher<View>) :
    BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

    override fun describeTo(description: Description) {
        description.appendText("has item at position $position: $itemMatcher")
    }

    override fun matchesSafely(recyclerView: RecyclerView): Boolean {
        val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
        return itemMatcher.matches(viewHolder!!.itemView)
    }
}

// https://medium.com/mindorks/some-useful-custom-espresso-matchers-in-android-33f6b9ca2240
private class RecyclerViewSizeMatcher(val expectedCount: Int) : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

    override fun describeTo(description: Description) {
        description.appendText("RecyclerView should have $expectedCount items")
    }

    override fun matchesSafely(recyclerView: RecyclerView): Boolean {
        return recyclerView.adapter!!.itemCount == expectedCount
    }
}
