package com.yochio.copy_conference2018.util.ext

import android.content.Context
import android.graphics.Point
import android.util.Size
import android.view.WindowManager

/**
 * Created by yochio on 2018/03/08.
 */

fun Context.displaySize(): Size {
    val point = Point()
    val manager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    manager.defaultDisplay.getSize(point)
    return Size(point.x, point.y)
}