package com.yochio.model


/**
 * Created by yochio on 2018/03/08.
 */

data class Speaker(
        val id: String,
        val name: String,
        val tagLine: String,
        val imageUrl: String,
        val twitterUrl: String?,
        val githubUrl: String?,
        val blogUrl: String?,
        val companyUrl: String?
)