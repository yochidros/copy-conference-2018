package com.yochio.copy_conference2018.util.rx

import io.reactivex.Scheduler

/**
 * Created by yochio on 2018/03/06.
 */

interface SchedulerProvider {
    fun ui(): Scheduler

    fun computation(): Scheduler

    fun newThread(): Scheduler

    fun io(): Scheduler
}