package com.yochio.copy_conference2018.data.db.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

/**
 * Created by yochio on 2018/03/08.
 */

data class SessionWithSpeakers(
        @Embedded var session: SessionEntity? = null,
        @Relation(
                parentColumn = "id",
                entityColumn = "sessionId",
                projection = ["speakerId"],
                entity = SessionSpeakerJoinEntity::class
        )
        var speakerIdList: List<String> = emptyList()
)