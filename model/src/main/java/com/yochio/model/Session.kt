package com.yochio.model

/**
 * Created by yochio on 2018/03/07.
 */

sealed class Session(
        open val id: String,
        open val dayNumber: Int

) {
    data class SpeechSession(
            override val id: String,
            override val dayNumber: Int,
            val title: String,
            val desc: String,
            val format: String,
            val language: String,
            val room: Room,
            val topic: Topic,
            val level: Level,
            val speakers: List<Speaker>
    ) : Session(id, dayNumber)
}