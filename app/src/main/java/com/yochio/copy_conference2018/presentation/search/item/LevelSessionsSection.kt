package com.yochio.copy_conference2018.presentation.search.item

import android.support.v4.app.Fragment
import com.xwray.groupie.Item
import com.xwray.groupie.Section
import com.yochio.model.Level
import com.yochio.model.Session

/**
 * Created by yochio on 2018/03/08.
 */

class LevelSessionsSection(private val fragment: Fragment) : Section() {

    fun updateSections(
            levelSessions: Map<Level, List<Session.SpeechSession>>
    ) {
        val list = mutableListOf<Item<*>>()

        levelSessions.keys.sortedBy { it.id }.map {
            level ->
            list.add(
                    HorizontalSessionsItem(
                            level,
                            levelSessions[level]!!,
                            fragment
                    )
            )
        }
        update(list)
    }
}