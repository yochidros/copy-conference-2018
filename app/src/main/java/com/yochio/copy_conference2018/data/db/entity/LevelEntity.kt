package com.yochio.copy_conference2018.data.db.entity

import android.arch.persistence.room.ColumnInfo

/**
 * Created by yochio on 2018/03/08.
 */

data class LevelEntity(
        @ColumnInfo(name = "level_id")
        var id: Int,
        @ColumnInfo(name = "level_name")
        var name: String
)