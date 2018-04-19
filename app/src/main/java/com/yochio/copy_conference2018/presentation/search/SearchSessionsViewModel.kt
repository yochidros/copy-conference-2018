package com.yochio.copy_conference2018.presentation.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.yochio.copy_conference2018.data.repository.SessionRepository
import com.yochio.copy_conference2018.presentation.Result
import com.yochio.copy_conference2018.presentation.common.mapper.toResult
import com.yochio.copy_conference2018.util.ext.toLiveData
import com.yochio.copy_conference2018.util.rx.SchedulerProvider
import com.yochio.model.Level
import com.yochio.model.Session
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by yochio on 2018/03/08.
 */

class SearchSessionsViewModel @Inject constructor(
        private val repository: SessionRepository,
        private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    val levelSessions: LiveData<Result<Map<Level, List<Session.SpeechSession>>>> by lazy {
        repository.levelSessions
                .toResult(schedulerProvider)
                .toLiveData()
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}