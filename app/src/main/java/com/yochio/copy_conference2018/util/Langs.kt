package com.yochio.copy_conference2018.util

import com.yochio.model.Lang
import java.util.*

/**
 * Created by yochio on 2018/03/06.
 */

fun lang(): Lang = if (Locale.JAPAN == Locale.getDefault()) {
    Lang.JA
} else {
    Lang.EN
}