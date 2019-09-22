package de.richargh.buggetfx.imports.ynab.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.richargh.buggetfx.imports.ynab.model.base.*
import de.richargh.buggetfx.imports.ynab.model.item.*
import java.io.IOException

class YCategoryDeserializer @JvmOverloads constructor(vc: Class<*>? = null): StdDeserializer<YCategory>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): YCategory {
        val node: JsonNode = jp.codec.readTree(jp)

        val entityVersion = node["entityVersion"].asText().toYEntityVersion()
        val masterCategoryId = node["masterCategoryId"].asText().toYEntityId()
        val entityId = node["entityId"].asText().toYEntityId()
        val type = node["type"].asText().toYCategoryType()
        val name = node["name"].asText()

        return YCategory(
                entityVersion,
                masterCategoryId,
                entityId,
                type,
                name)
    }
}