package io.sixtysix.happenings.utils

import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDateTime

class DateTimeAdapter : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    override fun serialize(src: LocalDateTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement =
        JsonPrimitive(src.toString())

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDateTime =
        LocalDateTime.parse(json!!.asString)

}