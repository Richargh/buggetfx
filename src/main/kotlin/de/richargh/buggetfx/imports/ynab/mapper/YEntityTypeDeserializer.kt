package de.richargh.buggetfx.imports.ynab.mapper

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.richargh.buggetfx.imports.ynab.model.item.*
import java.io.IOException

class YEntityTypeDeserializer @JvmOverloads constructor(vc: Class<*>? = null): StdDeserializer<YEntityType>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): YEntityType {
        val node: JsonNode = jp.codec.readTree(jp)
        return node.asText().toYEntityType()
    }
}