package de.richargh.buggetfx.imports.ynab.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.richargh.buggetfx.imports.ynab.model.base.YEntityVersion
import de.richargh.buggetfx.imports.ynab.model.item.YEntityVersions
import de.richargh.buggetfx.imports.ynab.model.item.YSubTransaction
import de.richargh.buggetfx.imports.ynab.model.root.YDevice
import de.richargh.buggetfx.imports.ynab.model.root.toYDeviceGuid
import de.richargh.buggetfx.imports.ynab.model.root.toYShortDeviceId
import java.io.IOException

class YDeviceDeserializer @JvmOverloads constructor(vc: Class<*>? = null): StdDeserializer<YDevice>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): YDevice {
        val node: JsonNode = jp.codec.readTree(jp)

        val shortDeviceId = node["shortDeviceId"].asText().toYShortDeviceId()
        val deviceGUID = node["deviceGUID"].asText().toYDeviceGuid()
        val friendlyName = node["friendlyName"].asText()
        val deviceType = node["deviceType"].asText()

        val knowledge = jp.codec.treeToValue(node["knowledge"], YEntityVersions::class.java)
        val knowledgeInFullBudgetFile = jp.codec.treeToValue(node["knowledgeInFullBudgetFile"], YEntityVersions::class.java)
                                        ?: YEntityVersions(emptyList())
        val hasFullKnowledge = node["hasFullKnowledge"].asBoolean()

        return YDevice(
                shortDeviceId,
                deviceGUID,
                friendlyName,
                deviceType,

                knowledge,
                knowledgeInFullBudgetFile,
                hasFullKnowledge)
    }
}