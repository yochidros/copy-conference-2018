package com.yochio.copy_conference2018.util.ext

import android.view.View
import android.view.ViewGroup

/**
 * Created by yochio on 2018/03/02.
 */

fun ViewGroup.eachChildView(function: (view: View) -> Unit) {
    (0 until childCount).forEach { function(getChildAt(it)) }
}