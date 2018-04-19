package com.yochio.copy_conference2018.util

/**
 * Created by yochio on 2018/03/08.
 */

object ViewSizeCalculator {
    fun calculateViewSizeByScreenAndCount(
            screenSize: Int,
            showItemCount: Float,
            itemMargin: Int = 0
    ): Int {
        return ((screenSize - itemMargin) / showItemCount).toInt()
    }
}