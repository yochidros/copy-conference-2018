package com.yochio.copy_conference2018.presentation.sessions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.yochio.copy_conference2018.data.repository.SessionRepository
import com.yochio.copy_conference2018.presentation.Result
import com.yochio.copy_conference2018.presentation.common.mapper.toResult
import com.yochio.copy_conference2018.util.ext.map
import com.yochio.copy_conference2018.util.ext.toLiveData
import com.yochio.copy_conference2018.util.rx.SchedulerProvider
import com.yochio.model.Session
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by yochio on 2018/03/09.
 */

class AllSessionsViewModel @Inject constructor(
        private val repository: SessionRepository,
        private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val focusCurrentSession: MutableLiveData<Boolean> = MutableLiveData()
    val refreshFocusCurrentSession: LiveData<Boolean> = focusCurrentSession

    val sessions: LiveData<Result<List<Session>>> by lazy {
        repository.sessions
                .toResult(schedulerProvider)
                .toLiveData()
    }

    val isLoading: LiveData<Boolean> by lazy {
        sessions.map { it.inProgress }
    }

    fun onSuccessFetchSessions() {
        refreshFocusCurrentSession()
    }

    private fun refreshFocusCurrentSession() {
        if (focusCurrentSession.value != true) {
            focusCurrentSession.value = true
        }
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}