package com.carles.common.ui.extensions

import android.os.SystemClock

interface ViewHolderClickListener<T> {
    fun onClick(item: T)
}

class DebounceClickListener<T>(private val onClickAction: (T) -> Unit, private val debounceTime: Long = 2000L) :
    ViewHolderClickListener<T> {

    private var lastClickTime: Long = 0

    override fun onClick(item: T) {
        if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
        lastClickTime = SystemClock.elapsedRealtime()
        onClickAction(item)
    }
}
