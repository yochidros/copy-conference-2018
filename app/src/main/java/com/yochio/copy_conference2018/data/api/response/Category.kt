package com.yochio.copy_conference2018.data.api.response

/**
 * Created by yochio on 2018/03/06.
 */

data class Category(
        val id: Int?,
        val sort: Int?,
        val title: String?,
        val items: List<CategoryItem?>?
)