package com.yochio.copy_conference2018.data

import android.arch.persistence.room.RoomDatabase
import com.yochio.copy_conference2018.data.api.response.Response
import com.yochio.copy_conference2018.data.api.response.mapper.toSessionEntities
import com.yochio.copy_conference2018.data.api.response.mapper.toSessionSpeakerJoinEntities
import com.yochio.copy_conference2018.data.api.response.mapper.toSpeakerEntities
import com.yochio.copy_conference2018.data.db.dao.SessionDao
import com.yochio.copy_conference2018.data.db.dao.SessionSpeakerJoinDao
import com.yochio.copy_conference2018.data.db.dao.SpeakerDao
import com.yochio.copy_conference2018.data.db.entity.*
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by yochio on 2018/03/07.
 */

class SessionRoomDatabase @Inject constructor(
        private val database: RoomDatabase,
        private val sessionDao: SessionDao,
        private val speakerDao: SpeakerDao,
        private val sessionSpeakerJoinDao: SessionSpeakerJoinDao
) : SessionDatabase {
    override fun getAllSessions(): Flowable<List<SessionWithSpeakers>> =
            sessionSpeakerJoinDao.getAllSessions()

    override fun getAllSpeaker(): Flowable<List<SpeakerEntity>> = speakerDao.getAllSpeaker()

    override fun getAllTopics(): Flowable<List<TopicEntity>> = sessionDao.getAllTopics()

    override fun getAllRoom(): Flowable<List<RoomEntity>> = sessionDao.getAllRooms()

    override fun save(response: Response) {
        database.runInTransaction {
            speakerDao.claerAndInsert(response.speakers.toSpeakerEntities())
            val sessions = response.sessions
            val sessionEntities = sessions.toSessionEntities(response.categories, response.rooms)
            sessionDao.clearAndInsert(sessionEntities)
            sessionSpeakerJoinDao.insert(sessions.toSessionSpeakerJoinEntities())
        }
    }
}