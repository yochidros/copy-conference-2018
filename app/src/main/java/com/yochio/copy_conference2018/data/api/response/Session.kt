package com.yochio.copy_conference2018.data.api.response

import org.threeten.bp.LocalDateTime
import se.ansman.kotshi.JsonSerializable

/**
 * Created by yochio on 2018/03/07.
 */

@JsonSerializable
data class Session(
        val id: String,
        val isServiceSession: Boolean?,
        val isPlenumSession: Boolean?,
        val speakers: List<String?>?,
        val description: String?,
        val startsAt: LocalDateTime?,
        val title: String?,
        val endsAd: LocalDateTime?,
        val roomId: Int?,
        val categoryItems: List<Int>?
)