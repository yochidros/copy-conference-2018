package com.yochio.copy_conference2018.data.repository

import android.support.annotation.CheckResult
import com.yochio.model.Level
import com.yochio.model.Room
import com.yochio.model.Session
import com.yochio.model.Topic
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by yochio on 2018/03/06.
 */

interface SessionRepository {
    val topics: Flowable<List<Topic>>
    val sessions: Flowable<List<Session>>
    val rooms: Flowable<List<Room>>
    val levelSessions: Flowable<Map<Level, List<Session.SpeechSession>>>
    val roomSessions: Flowable<Map<Room, List<Session>>>

    @CheckResult
    fun refreashSessions(): Completable
}