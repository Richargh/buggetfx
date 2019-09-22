package de.richargh.buggetfx.imports.ynab.mapper

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.richargh.buggetfx.imports.ynab.model.base.*
import de.richargh.buggetfx.imports.ynab.model.item.*
import java.io.IOException

class YAccountDeserializer @JvmOverloads constructor(vc: Class<*>? = null): StdDeserializer<YAccount>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): YAccount {
        val node: JsonNode = jp.codec.readTree(jp)

        val entityVersion = node["entityVersion"].asText().toYEntityVersion()
        val entityId = node["entityId"].asText().toYEntityId()

        val accountType = node["accountType"].asText().toYAccountType()
        val accountName = node["accountName"].asText()
        val lastReconciledBalance = node["lastReconciledBalance"].asDouble()

        return YAccount(
                entityVersion,
                entityId,
                accountType,
                accountName,
                lastReconciledBalance)
    }
}