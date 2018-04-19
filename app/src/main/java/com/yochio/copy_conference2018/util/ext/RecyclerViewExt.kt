package com.yochio.copy_conference2018.util.ext

import android.support.annotation.DrawableRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by yochio on 2018/03/07.
 */

fun RecyclerView.setLinearDivider(
        @DrawableRes drawableResId: Int,
        linearLayoutManager: LinearLayoutManager
) {
    val context = this.context
    this.addItemDecoration(android.support.v7.widget.DividerItemDecoration(context, linearLayoutManager.orientation).apply {
        setDrawable(android.support.v4.content.ContextCompat.getDrawable(context, drawableResId)!!)
    })
}
