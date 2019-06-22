package io.sixtysix.happenings.utils

import com.google.gson.*
import org.joda.time.DateTime
import java.lang.reflect.Type

class DateTimeAdapter : JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

    override fun serialize(src: DateTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement =
        JsonPrimitive(src.toString())

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): DateTime =
        DateTime(json!!.asString)

}