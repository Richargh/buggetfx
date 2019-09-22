package de.richargh.buggetfx.imports.ynab.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.richargh.buggetfx.imports.ynab.model.root.YDevice
import de.richargh.buggetfx.imports.ynab.model.root.toYShortDeviceId
import java.io.IOException

class YDeviceDeserializer @JvmOverloads constructor(vc: Class<*>? = null): StdDeserializer<YDevice>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): YDevice {
        val node: JsonNode = jp.codec.readTree(jp)

        val shortDeviceId = node["shortDeviceId"].asText().toYShortDeviceId()

        return YDevice(
                shortDeviceId)
    }
}