package com.yochio.copy_conference2018.util.ext

import android.content.res.Resources
import android.support.annotation.DimenRes
import android.util.TypedValue

/**
 * Created by yochio on 2018/03/02.
 */
fun Resources.getFloat(@DimenRes resourceId: Int): Float {
    val outValue = TypedValue()
    getValue(resourceId, outValue, true)
    return outValue.float
}