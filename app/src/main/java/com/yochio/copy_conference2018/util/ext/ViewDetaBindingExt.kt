package com.yochio.copy_conference2018.util.ext

import android.content.Context
import android.databinding.ViewDataBinding

/**
 * Created by yochio on 2018/03/08.
 */

val ViewDataBinding.context: Context
    get() = root.context