package com.yochio.copy_conference2018.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey

/**
 * Created by yochio on 2018/03/08.
 */

@Entity(tableName = "session_speaker_join", primaryKeys = ["sessionId", "speakerId"],
        foreignKeys = [
            (ForeignKey(
                    entity = SessionEntity::class,
                    parentColumns = arrayOf("id"),
                    childColumns = arrayOf("sessionId"),
                    onDelete = ForeignKey.CASCADE
            )),
            (ForeignKey(
                    entity = SpeakerEntity::class,
                    parentColumns = arrayOf("id"),
                    childColumns = arrayOf("speakerId"),
                    onDelete = ForeignKey.CASCADE
            ))
        ]
)
class SessionSpeakerJoinEntity(
        val sessionId: String,
        @ColumnInfo(index = true)
        val speakerId: String
)