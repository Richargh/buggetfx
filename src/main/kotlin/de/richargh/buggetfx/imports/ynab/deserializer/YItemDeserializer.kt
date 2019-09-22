package de.richargh.buggetfx.imports.ynab.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.richargh.buggetfx.imports.ynab.model.item.*
import java.io.IOException

class YItemDeserializer @JvmOverloads constructor(vc: Class<*>? = null): StdDeserializer<YItem>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): YItem {
        val node: JsonNode = jp.codec.readTree(jp)
        val entityType = node["entityType"].asText().toYEntityType()

        return when (entityType) {
            YEntityType.PAYEE            -> jp.codec.treeToValue(node, YPayee::class.java)
            YEntityType.TRANSACTION      -> jp.codec.treeToValue(node, YTransaction::class.java)
            YEntityType.ACCOUNT          -> jp.codec.treeToValue(node, YAccount::class.java)
            YEntityType.BUDGET_META_DATA -> jp.codec.treeToValue(node, YBudgetMetaData::class.java)
            YEntityType.MASTER_CATEGORY  -> jp.codec.treeToValue(node, YMasterCategory::class.java)
            YEntityType.CATEGORY         -> jp.codec.treeToValue(node, YCategory::class.java)
            else                         -> YItemUnknown(entityType)
        }
    }
}
