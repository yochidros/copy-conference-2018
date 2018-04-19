package com.yochio.copy_conference2018.presentation.search.item

import com.xwray.groupie.Item
import com.xwray.groupie.Section
import com.yochio.model.Topic

/**
 * Created by yochio on 2018/03/06.
 */

class TopicsGroup : Section() {

    fun updateTopics(topics: List<Topic>) {
        val list = mutableListOf<Item<*>>()
        topics.sortedBy { it.id }.mapTo(list) {
            topic ->
            TopicItem(topic)
        }
        update(list)
    }
}