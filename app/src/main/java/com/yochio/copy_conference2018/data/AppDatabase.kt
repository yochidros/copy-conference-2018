package com.yochio.copy_conference2018.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.yochio.copy_conference2018.data.db.dao.SessionDao
import com.yochio.copy_conference2018.data.db.dao.SessionSpeakerJoinDao
import com.yochio.copy_conference2018.data.db.dao.SpeakerDao
import com.yochio.copy_conference2018.data.db.entity.SessionEntity
import com.yochio.copy_conference2018.data.db.entity.SessionSpeakerJoinEntity
import com.yochio.copy_conference2018.data.db.entity.SpeakerEntity
import com.yochio.copy_conference2018.data.db.entity.mapper.Converters

/**
 * Created by yochio on 2018/03/06.
 */

@Database(
        entities = [
            (SessionEntity::class),
            (SpeakerEntity::class),
            (SessionSpeakerJoinEntity::class)
        ],
        version = 6
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
    abstract fun speakerDao(): SpeakerDao
    abstract fun sessionSpeakerDao(): SessionSpeakerJoinDao
}