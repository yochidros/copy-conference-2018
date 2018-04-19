package com.yochio.copy_conference2018.util.ext

import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

/**
 * Created by yochio on 2018/03/08.
 */

fun LocalDateTime.toUnixMills(): Long {
    return atZone(ZoneId.of("JST", ZoneId.SHORT_IDS)).toInstant().toEpochMilli()
}