package com.yochio.copy_conference2018.data.db.dao

import android.arch.persistence.room.*
import com.yochio.copy_conference2018.data.db.entity.SpeakerEntity
import io.reactivex.Flowable

/**
 * Created by yochio on 2018/03/08.
 */

@Dao abstract class SpeakerDao {
    @Query("SELECT * FROM speaker")
    abstract fun getAllSpeaker(): Flowable<List<SpeakerEntity>>

    @Query("DELETE FROM speaker")
    abstract fun deleetAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(speakers: List<SpeakerEntity>)

    @Transaction open fun claerAndInsert(newSessions: List<SpeakerEntity>) {
        deleetAll()
        insert(newSessions)
    }
}