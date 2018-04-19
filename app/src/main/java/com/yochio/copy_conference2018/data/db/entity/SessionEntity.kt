package com.yochio.copy_conference2018.data.db.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.LocalDateTime

/**
 * Created by yochio on 2018/03/06.
 */

@Entity(tableName = "session")
data class SessionEntity(
        @PrimaryKey var id: String,
        var title: String,
        var desc: String,
        var sessionFormat: String,
        var language: String,
        @Embedded var topic: TopicEntity,
        @Embedded var level: LevelEntity,
        @Embedded var room: RoomEntity
)