package com.yochio.copy_conference2018.data.db.entity

import android.arch.persistence.room.ColumnInfo

/**
 * Created by yochio on 2018/03/09.
 */

data class RoomEntity(
        @ColumnInfo(name = "room_id")
        val id: Int,

        @ColumnInfo(name = "room_name")
        val name: String
)