package de.richargh.buggetfx.imports.ynab.mapper

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.richargh.buggetfx.imports.ynab.model.base.*
import de.richargh.buggetfx.imports.ynab.model.item.*
import java.io.IOException

class YBudgetMetaDataDeserializer @JvmOverloads constructor(vc: Class<*>? = null): StdDeserializer<YBudgetMetaData>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): YBudgetMetaData {
        val node: JsonNode = jp.codec.readTree(jp)
        val entityVersion = node["entityVersion"].asText().toYEntityVersion()

        return YBudgetMetaData(
                entityVersion)
    }
}
