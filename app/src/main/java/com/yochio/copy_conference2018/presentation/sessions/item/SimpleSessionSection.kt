package com.yochio.copy_conference2018.presentation.sessions.item

import com.xwray.groupie.Item
import com.xwray.groupie.Section
import com.yochio.model.Session

/**
 * Created by yochio on 2018/03/08.
 */

class SimpleSessionSection : Section() {
    fun updateSessions(
            sessions: List<Session>,
            userIdInDetail: String? = null
    ) {
        val sessionItems = sessions.map {
            when (it) {
                is Session.SpeechSession -> {
                    SpeechSessionItem(
                            session = it,
                            userIdInDetail = userIdInDetail
                    ) as Item<*>
                }
            }
        }
        update(sessionItems)
    }
}