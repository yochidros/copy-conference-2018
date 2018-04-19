package com.yochio.copy_conference2018.data.api.response


/**
 * Created by yochio on 2018/03/06.
 */

data class Response(
        val sessions: List<Session>?,
        val rooms: List<Room>?,
        val categories: List<Category>?,
        val speakers: List<Speaker>?
)