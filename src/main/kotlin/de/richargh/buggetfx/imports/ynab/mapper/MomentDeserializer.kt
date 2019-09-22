package de.richargh.buggetfx.imports.ynab.mapper

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.richargh.buggetfx.time.Moment
import java.io.IOException
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.ofPattern
import java.time.format.DateTimeFormatterBuilder

class MomentDeserializer @JvmOverloads constructor(vc: Class<*>? = null): StdDeserializer<Moment>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): Moment {
        val node: JsonNode = jp.codec.readTree(jp)
        val dateString = node.asText()
        return parseMoment(dateString)
    }
}

private fun parseMoment(dateString: String): Moment {
    val zonedDateTime = ZonedDateTime.parse(dateString,
                                            DATE_TIME_FORMATTER)
    return Moment(zonedDateTime)
}

// more formate online: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
private val DATE_TIME_FORMATTER = DateTimeFormatterBuilder()
        .appendOptional(ofPattern("EEE MMM d HH:mm:ss 'GMT'Z yyyy")) // Sun Sep 8 14:56:08 GMT+0200 2019
        .appendOptional(ofPattern("yyyy-MM-dd HH:mm:ss")) // 2013-05-04 18:50:02
        .toFormatter()
