package de.richargh.buggetfx.imports.ynab.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.richargh.buggetfx.imports.ynab.asTextOrNull
import de.richargh.buggetfx.imports.ynab.model.base.*
import de.richargh.buggetfx.imports.ynab.model.entity.*
import de.richargh.buggetfx.time.Moment
import java.io.IOException
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

class YPayeeDeserializer @JvmOverloads constructor(vc: Class<*>? = null): StdDeserializer<YPayee>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): YPayee {
        val node: JsonNode = jp.readValueAsTree()

        return payee(node)
    }

    private fun payee(node: JsonNode): YPayee {
        val autoFillMemo = node["autoFillMemo"].asTextOrNull()
        val name = node["name"].asText()
        val enabled = node["enabled"].asBoolean()
        val entityVersion = node["entityVersion"].asText().toYEntityVersion()
        val entityId = node["entityId"].asText().toYEntityId()
        val autoFillCategoryId = node["autoFillCategoryId"].asText().toYAutofillCategoryId()
        val autoFillAmount = node["autoFillAmount"].asDouble()

        return YPayee(
                autoFillMemo,
                name,
                enabled,
                entityVersion,
                entityId,
                autoFillCategoryId,
                autoFillAmount)
    }
}