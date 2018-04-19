package com.yochio.copy_conference2018.data.api.response.mapper

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by yochio on 2018/03/07.
 */


class LocalDateTimeAdapter : JsonAdapter<LocalDateTime>() {
    override fun toJson(writer: JsonWriter, value: LocalDateTime?) {
        if (value == null) {
            writer.nullValue()
        } else {
            writer.value(value.format(FORMATTER))
        }
    }

    override fun fromJson(reader: JsonReader): LocalDateTime? = when (reader.peek()) {
        JsonReader.Token.NULL -> reader.nextNull()
        else -> {
            val dateString = reader.nextString()
            parseDateString(dateString)
        }
    }

    companion object {
        private val FORMATTER: DateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        fun parseDateString(dateString: String?): LocalDateTime =
                LocalDateTime.parse(dateString, FORMATTER)
    }
}