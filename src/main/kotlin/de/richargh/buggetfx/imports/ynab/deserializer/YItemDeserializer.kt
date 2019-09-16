package de.richargh.buggetfx.imports.ynab.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.richargh.buggetfx.imports.ynab.model.base.toYEntityId
import de.richargh.buggetfx.imports.ynab.model.base.toYEntityType
import de.richargh.buggetfx.imports.ynab.model.base.toYEntityVersion
import de.richargh.buggetfx.imports.ynab.model.entity.YItem
import de.richargh.buggetfx.imports.ynab.model.entity.YItemAutofill
import de.richargh.buggetfx.imports.ynab.model.diff.toYAutofillCategoryId
import java.io.IOException

class YItemDeserializer @JvmOverloads constructor(vc: Class<*>? = null): StdDeserializer<YItem>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): YItem {
        val node: JsonNode = jp.codec.readTree(jp)
        val autoFillMemo = node["autoFillMemo"].asText()
        val name = node["name"].asText()
        val enabled = node["enabled"].asBoolean()
        val entityVersion = node["entityVersion"].asText().toYEntityVersion()
        val entityId = node["entityId"].asText().toYEntityId()
        val autoFillCategoryId = node["autoFillCategoryId"].asText().toYAutofillCategoryId()
        val entityType = node["entityType"].asText().toYEntityType()
        val autoFillAmount = node["autoFillAmount"].asDouble()

        return YItemAutofill(
                autoFillMemo,
                name,
                enabled,
                entityVersion,
                entityId,
                autoFillCategoryId,
                entityType,
                autoFillAmount)
    }
}