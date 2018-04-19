package com.yochio.copy_conference2018.data.db.entity.mapper

import android.arch.persistence.room.TypeConverter
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId


/**
 * Created by yochio on 2018/03/07.
 */

object Converters {
    @JvmStatic
    @TypeConverter
    fun fromTimeStamp(value: Long?): LocalDateTime? {
        if (value == null) {
            return null
        }
        return LocalDateTime.ofInstant(
                Instant.ofEpochSecond(value),
                ZoneId.of("JST", ZoneId.SHORT_IDS)
        )
    }

    @JvmStatic
    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? =
            date?.atZone(ZoneId.of("JST", ZoneId.SHORT_IDS))?.toEpochSecond()
}