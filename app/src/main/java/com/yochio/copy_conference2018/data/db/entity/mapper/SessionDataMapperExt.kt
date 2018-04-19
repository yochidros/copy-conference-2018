package com.yochio.copy_conference2018.data.db.entity.mapper

import com.yochio.copy_conference2018.data.db.entity.*
import com.yochio.model.*
import io.reactivex.Flowable

/**
 * Created by yochio on 2018/03/07.
 */

fun SessionWithSpeakers.toSession(
        speakerEntities: List<SpeakerEntity>
): Session.SpeechSession {
    val sessionEntity = session!!
    require(speakerIdList.isNotEmpty())
    val speakers = speakerIdList.map { speakerId ->
        val speakerEntity = speakerEntities.first { it.id == speakerId }
        speakerEntity.toSpeaker()
    }
    require(speakers.isNotEmpty())
    return Session.SpeechSession(
            id = sessionEntity.id,
            dayNumber = 1,
            title = sessionEntity.title,
            desc = sessionEntity.desc,
            format = sessionEntity.sessionFormat,
            language = sessionEntity.language,
            room = Room(sessionEntity.room.id, sessionEntity.room.name),
            topic = Topic(sessionEntity.topic.id, sessionEntity.topic.name),
            level = Level.of(sessionEntity.level.id, sessionEntity.level.name),
            speakers = speakers
    )
}

fun SpeakerEntity.toSpeaker(): Speaker = Speaker(
        id = id,
        name = name,
        tagLine = tagLine,
        imageUrl = imageUrl,
        twitterUrl = twitterUrl,
        companyUrl = companyUrl,
        githubUrl = githubUrl,
        blogUrl = blogUrl
)

fun Flowable<List<RoomEntity>>.toRooms(): Flowable<List<Room>> = map { roomEntities ->
    roomEntities.toRooms()
}

fun Flowable<List<TopicEntity>>.toTopics(): Flowable<List<Topic>> =
        map { topicEntities ->
            topicEntities.toTopics()
        }

fun List<RoomEntity>.toRooms() = map { Room(it.id, it.name) }

fun List<TopicEntity>.toTopics() = map { Topic(it.id, it.name) }

