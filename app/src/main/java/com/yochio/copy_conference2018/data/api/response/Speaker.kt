package com.yochio.copy_conference2018.data.api.response

/**
 * Created by yochio on 2018/03/08.
 */

data class Speaker(
        val firstName: String?,
        val lastName: String?,
        val profilePicture: String?,
        val sessions: List<Int?>?,
        val tagLine: String?,
        val isTopSpeaker: Boolean?,
        val bio: String?,
        val fullName: String?,
        val links: List<Link?>?,
        val id: String?
)