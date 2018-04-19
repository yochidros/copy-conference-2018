package com.yochio.model

/**
 * Created by yochio on 2018/03/06.
 */

data class Topic(
        val id: Int,
        val name: String
) {
    fun getNameByLang(lang: Lang): String = name
            .split(" / ")
            .getOrElse(lang.ordinal, { name })
            .trim()
}