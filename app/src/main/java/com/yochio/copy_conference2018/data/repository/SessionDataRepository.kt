package com.yochio.copy_conference2018.data.repository

import com.yochio.copy_conference2018.data.SessionDatabase
import com.yochio.copy_conference2018.data.api.DroidKaigApi
import com.yochio.copy_conference2018.data.db.entity.mapper.toRooms
import com.yochio.copy_conference2018.data.db.entity.mapper.toSession
import com.yochio.copy_conference2018.data.db.entity.mapper.toTopics
import com.yochio.copy_conference2018.util.rx.SchedulerProvider
import com.yochio.model.Level
import com.yochio.model.Room
import com.yochio.model.Session
import com.yochio.model.Topic
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.rxkotlin.Flowables
import javax.inject.Inject

/**
 * Created by yochio on 2018/03/06.
 */

class SessionDataRepository @Inject constructor(
        private val api: DroidKaigApi,
        private val sessionDatabase: SessionDatabase,
        private val schedulerProvider: SchedulerProvider
) : SessionRepository {

    override val rooms: Flowable<List<Room>> =
            sessionDatabase.getAllRoom().toRooms().filter { it.isNotEmpty() }

    override val topics: Flowable<List<Topic>> =
            sessionDatabase.getAllTopics().toTopics()

    override val sessions: Flowable<List<Session>> =
            (Flowables.combineLatest(
                    sessionDatabase.getAllSessions()
                            .filter { it.isNotEmpty() },
                    sessionDatabase.getAllSpeaker()
                            .filter { it.isNotEmpty() },
                    { sessionsEntities, speakerEntities ->
                        val speakerSessions = sessionsEntities
                                .map { it.toSession(speakerEntities) }
                                .sortedWith(compareBy(
                                        { it.room.id }
                                ))
                        speakerSessions
                    }) as Flowable<List<Session>>).subscribeOn(schedulerProvider.computation())

    override val levelSessions: Flowable<Map<Level, List<Session.SpeechSession>>> =
            sessions.map { sessionList ->
                sessionList.filterIsInstance<Session.SpeechSession>()
                        .groupBy { it.level }
            }

    override val roomSessions: Flowable<Map<Room, List<Session>>> =
            sessions.map { sessionList ->
                sessionList
                        .filter {
                            if (it is Session.SpeechSession) {
                                it.room != null
                            } else {
                                true
                            }
                        }
                        .groupBy {
                            when (it) {
                                is Session.SpeechSession -> it.room
                            }
                        }
            }

    override fun refreashSessions(): Completable {
        return api.getSessions()
                .doOnSuccess { response ->
                    println(response.speakers)
                    sessionDatabase.save(response)
                }
                .subscribeOn(schedulerProvider.computation())
                .toCompletable()
    }
}