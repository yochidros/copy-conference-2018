package com.yochio.copy_conference2018.util

import android.app.ProgressDialog.show
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import kotlin.math.min

/**
 * Created by yochio on 2018/03/09.
 */

class ProgressTimeLatch(
        private val delayMs: Long = 500,
        private val minShowTime: Long = 500,
        private val viewRefreshingToggle: ((Boolean) -> Unit)
) {
    private val handler = Handler(Looper.getMainLooper())
    private var showTime = 0L

    private val delayedShow = Runnable(this::show)
    private val delayedHide = Runnable(this::hideAndReset)

    var loading = false
        set(value) {
            if (field != value) {
                field = value
                handler.removeCallbacks(delayedShow)
                handler.removeCallbacks(delayedHide)

                if (value) {
                    handler.postDelayed(delayedShow, delayMs)
                } else if (showTime >= 0) {
                    val showTime = SystemClock.uptimeMillis() - showTime
                    if (showTime < minShowTime) {
                        handler.postDelayed(delayedHide, minShowTime - showTime)
                    } else {
                        hideAndReset()
                    }
                } else {
                    hideAndReset()
                }
            }
        }

    private fun show() {
        viewRefreshingToggle(true)
        showTime = SystemClock.uptimeMillis()
    }

    private fun hideAndReset() {
        viewRefreshingToggle(false)
        showTime = 0
    }
}