package com.yochio.copy_conference2018.util.ext

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * Created by yochio on 2018/03/02.
 */

@SuppressLint("RestrictedApi")
fun BottomNavigationView.disableShiftMode() {

    val menuView = this.getChildAt(0) as BottomNavigationMenuView
    try {
        val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false
        menuView.eachChildView {
            val item = it as BottomNavigationItemView
            item.removeViewAt(1)
            val lp = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)

            lp.gravity = Gravity.CENTER
            item.getChildAt(0).layoutParams = lp

            val topMargin = item.javaClass.getDeclaredField("mDefaultMargin")
            topMargin.isAccessible = true
            topMargin.setInt(item, 0)
            topMargin.isAccessible = false

            item.setShiftingMode(false)

            item.setChecked(item.itemData!!.isChecked)
        }
    } catch (e: NoSuchFieldException) {
        println(e)
    } catch (e: IllegalAccessException) {
        println(e)
    }
}