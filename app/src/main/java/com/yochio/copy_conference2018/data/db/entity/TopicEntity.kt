package com.yochio.copy_conference2018.data.db.entity

import android.arch.persistence.room.ColumnInfo

/**
 * Created by yochio on 2018/03/06.
 */

data class TopicEntity(
        @ColumnInfo(name = "topic_id")
        var id: Int,
        @ColumnInfo(name = "topic_name")
        var name: String
)