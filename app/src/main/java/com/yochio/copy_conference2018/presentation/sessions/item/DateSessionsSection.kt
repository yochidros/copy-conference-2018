package com.yochio.copy_conference2018.presentation.sessions.item

import android.arch.lifecycle.LiveData
import com.xwray.groupie.Item
import com.xwray.groupie.Section
import com.yochio.copy_conference2018.util.ext.map
import com.yochio.model.Session
import java.util.*

/**
 * Created by yochio on 2018/03/09.
 */

class DateSessionsSection : Section() {
    fun updateSessions(
            sessions: List<Session>
    ) {
        val sessionItems = sessions.map {
            when (it) {
                is Session.SpeechSession -> {
                    SpeechSessionItem(
                            session = it
                    ) as SessionItem
                }
            }
        }

        val sessionsList = mutableListOf<Item<*>>()

        sessionsList.addAll(sessionItems.toMutableList() as List<Item<*>>)

        update(sessionsList)
    }
}