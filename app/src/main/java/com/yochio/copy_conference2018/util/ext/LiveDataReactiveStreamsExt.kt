package com.yochio.copy_conference2018.util.ext

import android.arch.lifecycle.LiveData
import org.reactivestreams.Publisher
import android.arch.lifecycle.LiveDataReactiveStreams


/**
 * Created by yochio on 2018/03/06.
 */

fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this) as LiveData<T>