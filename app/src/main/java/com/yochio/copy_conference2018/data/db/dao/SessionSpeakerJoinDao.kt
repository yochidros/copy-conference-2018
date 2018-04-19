package com.yochio.copy_conference2018.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.yochio.copy_conference2018.data.db.entity.SessionSpeakerJoinEntity
import com.yochio.copy_conference2018.data.db.entity.SessionWithSpeakers
import io.reactivex.Flowable
import org.intellij.lang.annotations.Language

/**
 * Created by yochio on 2018/03/08.
 */

@Dao abstract class SessionSpeakerJoinDao {
    @Language("RoomSql")
    @Transaction
    @Query("SELECT * FROM session")
    abstract fun getAllSessions(): Flowable<List<SessionWithSpeakers>>

    @Insert abstract fun insert(sessionSpeakerJoin: List<SessionSpeakerJoinEntity>)
}