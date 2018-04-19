package com.yochio.copy_conference2018.util.ext

import android.os.Build
import android.view.View

/**
 * Created by yochio on 2018/03/02.
 */

fun View.setVisible(visible: Boolean) {
    if (visible) {
        toVisible()
    } else {
        toGone()
    }
}

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}

fun View.isGone() = visibility == View.GONE

fun View.isVisible() = visibility == View.VISIBLE

var View.elevationForPostLollipop: Float
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        elevation
    } else {
        0F
    }
    set(value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            elevation = value
        }
    }