package com.carles.common.ui

import android.content.res.Resources
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

private const val DEFAULT_DEBOUNCE_TIME = 2_000L

inline fun Fragment.consumeMenuClick(f: () -> Unit): Boolean {
    f()
    return true
}

fun AppCompatActivity.getStrings(ids: List<Int>) = ids.map { getString(it) }.toTypedArray()

val Int.px
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.dp
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

fun View.setDebounceClickListener(action: () -> Unit) {
    setDebounceClickListener(action, DEFAULT_DEBOUNCE_TIME)
}

fun View.setDebounceClickListener(action: () -> Unit, debounceTime: Long) {
    var lastClickTime: Long = 0
    this.setOnClickListener(object : View.OnClickListener {
        override fun onClick(v: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            lastClickTime = SystemClock.elapsedRealtime()
            action()
        }
    })
}

fun Fragment.safeNavigate(fragmentId: Int, actionResourceId: Int, bundle: Bundle?) {
    if (findNavController().currentDestination?.id == fragmentId) {
        findNavController().navigate(actionResourceId, bundle)
    }
}