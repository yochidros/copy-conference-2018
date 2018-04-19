package com.yochio.copy_conference2018.data.db.dao

import android.arch.persistence.room.*
import com.yochio.copy_conference2018.data.db.entity.RoomEntity
import com.yochio.copy_conference2018.data.db.entity.SessionEntity
import com.yochio.copy_conference2018.data.db.entity.TopicEntity
import io.reactivex.Flowable

/**
 * Created by yochio on 2018/03/06.
 */

@Dao abstract class SessionDao {

    @Query("DELETE FROM session")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(sessions: List<SessionEntity>)

    @Transaction open fun clearAndInsert(newSessions: List<SessionEntity>) {
        deleteAll()
        insert(newSessions)
    }
    @Query("SELECT * FROM session")
    abstract fun getAllSessions(): Flowable<List<SessionEntity>>

    @Query("SELECT room_id, room_name FROM session GROUP BY room_id ORDER BY room_id")
    abstract fun getAllRooms(): Flowable<List<RoomEntity>>

    @Query("SELECT topic_id, topic_name FROM session GROUP BY topic_id ORDER BY topic_id")
    abstract fun getAllTopics(): Flowable<List<TopicEntity>>
}