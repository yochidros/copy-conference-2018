package com.yochio.copy_conference2018.presentation.common.mapper

import com.yochio.copy_conference2018.presentation.Result
import com.yochio.copy_conference2018.util.rx.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.internal.operators.single.SingleInternalHelper.toObservable

/**
 * Created by yochio on 2018/03/06.
 */

fun <T> Flowable<T>.toResult(schedulerProvider: SchedulerProvider): Flowable<Result<T>>  {
    return compose { item ->
        item
                .map { Result.success(it) }
                .onErrorReturn { e -> Result.failure(e.message, e) }
                .observeOn(schedulerProvider.ui())
                .startWith(Result.inProgress())
    }
}

fun <T> Observable<T>.toResult(schedulerProvider: SchedulerProvider): Observable<Result<T>> {
    return compose { item ->
        item.map { Result.success(it) }
                .onErrorReturn { e -> Result.failure(e.message ?: "unkonwn", e) }
                .observeOn(schedulerProvider.ui())
                .startWith(Result.inProgress())
    }
}

fun <T> Completable.toResult(schedulerProvider: SchedulerProvider): Observable<Result<T>> {
    return toObservable<T>().toResult(schedulerProvider)
}