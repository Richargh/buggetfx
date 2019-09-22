package de.richargh.buggetfx.imports.ynab.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.richargh.buggetfx.imports.ynab.model.base.*
import de.richargh.buggetfx.imports.ynab.model.item.*
import java.io.IOException

class YMasterCategoryDeserializer @JvmOverloads constructor(vc: Class<*>? = null): StdDeserializer<YMasterCategory>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): YMasterCategory {
        val node: JsonNode = jp.codec.readTree(jp)

        val entityVersion = node["entityVersion"].asText().toYEntityVersion()
        val entityId = node["entityId"].asText().toYEntityId()
        val type = node["type"].asText().toYCategoryType()
        val name = node["name"].asText()

        val subCategories = mutableListOf<YCategory>()
        node["subCategories"].elements().forEach {
            subCategories.add(jp.codec.treeToValue(it, YCategory::class.java))
        }

        return YMasterCategory(
                entityVersion,
                entityId,
                type,
                name,
                subCategories)
    }
}