package de.richargh.buggetfx.imports.ynab.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.richargh.buggetfx.imports.ynab.asTextOrNull
import de.richargh.buggetfx.imports.ynab.model.base.*
import de.richargh.buggetfx.imports.ynab.model.item.*
import de.richargh.buggetfx.time.Moment
import java.io.IOException
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

class YTransactionDeserializer @JvmOverloads constructor(vc: Class<*>? = null): StdDeserializer<YTransaction>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): YTransaction {
        val node: JsonNode = jp.readValueAsTree()

        val date = parseMoment(node["date"].asText())
        val entityId = node["entityId"].asText().toYEntityId()
        val categoryId = node["categoryId"].asText().toYCategoryId()
        val targetAccountId = node["targetAccountId"].asTextOrNull()?.toYAccountId()
        val payeeId = node["payeeId"].asText().toYPayeeId()
        val entityVersion = node["entityVersion"].asText().toYEntityVersion()
        val amount = node["amount"].asDouble()
        val accountId = node["accountId"].asText().toYAccountId()
        val memo = node["memo"].asTextOrNull()

        val matchedTransactions = mutableListOf<YMatchedTransaction>()
        node["matchedTransactions"]?.elements()?.forEach {
            matchedTransactions.add(jp.codec.treeToValue(it, YMatchedTransaction::class.java))
        }

        val subTransactions = mutableListOf<YSubTransaction>()
        node["subTransactions"]?.elements()?.forEach {
            subTransactions.add(jp.codec.treeToValue(it, YSubTransaction::class.java))
        }

        return YTransaction(
                date,
                entityId,
                categoryId,
                targetAccountId,
                payeeId,
                entityVersion,
                amount,
                accountId,
                memo,
                matchedTransactions,
                subTransactions)
    }

}

private val DATE_TIME_FORMATTER = DateTimeFormatterBuilder()
        .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd")) // 2013-05-04
        .toFormatter()

private fun parseMoment(date: String): Moment {
    val localDate = LocalDate.parse(date, DATE_TIME_FORMATTER)
    return Moment(
            ZonedDateTime.of(localDate, LocalTime.of(0, 0, 0), ZoneOffset.UTC))
}