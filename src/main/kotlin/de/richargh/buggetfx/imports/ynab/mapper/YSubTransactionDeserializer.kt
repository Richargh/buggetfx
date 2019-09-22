package de.richargh.buggetfx.imports.ynab.mapper

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.richargh.buggetfx.imports.ynab.asTextOrNull
import de.richargh.buggetfx.imports.ynab.model.base.*
import de.richargh.buggetfx.imports.ynab.model.item.*
import java.io.IOException

class YSubTransactionDeserializer @JvmOverloads constructor(vc: Class<*>? = null): StdDeserializer<YSubTransaction>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): YSubTransaction {
        val node: JsonNode = jp.readValueAsTree()

        val entityVersion = node["entityVersion"].asText().toYEntityVersion()

        val parentTransactionId = node["parentTransactionId"].asText().toYEntityId()
        val entityId = node["entityId"].asText().toYEntityId()
        val categoryId = node["categoryId"].asText().toYCategoryId()

        val amount = node["amount"].asDouble()
        val memo = node["memo"].asTextOrNull()

        return YSubTransaction(
                parentTransactionId,
                entityId,
                categoryId,
                entityVersion,
                amount,
                memo)
    }
}