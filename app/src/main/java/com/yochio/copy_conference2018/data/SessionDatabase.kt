package com.yochio.copy_conference2018.data

import com.yochio.copy_conference2018.data.api.response.Response
import com.yochio.copy_conference2018.data.db.entity.*
import io.reactivex.Flowable

/**
 * Created by yochio on 2018/03/06.
 */

interface SessionDatabase {
    fun getAllSessions(): Flowable<List<SessionWithSpeakers>>
    fun getAllSpeaker(): Flowable<List<SpeakerEntity>>
    fun getAllTopics(): Flowable<List<TopicEntity>>
    fun getAllRoom(): Flowable<List<RoomEntity>>
    fun save(response: Response)
}