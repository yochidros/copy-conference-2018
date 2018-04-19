package com.yochio.copy_conference2018.presentation.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.yochio.copy_conference2018.data.repository.SessionRepository
import com.yochio.copy_conference2018.presentation.Result
import com.yochio.copy_conference2018.presentation.common.mapper.toResult
import com.yochio.copy_conference2018.util.ext.toLiveData
import com.yochio.copy_conference2018.util.rx.SchedulerProvider
import com.yochio.model.Session
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by yochio on 2018/03/09.
 */

class SessionDetailViewModel @Inject constructor(
        private val repository: SessionRepository,
        private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val sessions: LiveData<Result<List<Session.SpeechSession>>> by lazy {
        repository.sessions
                .map { sessions ->
                    sessions.filterIsInstance<Session.SpeechSession>()
                }
                .toResult(schedulerProvider)
                .toLiveData()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}