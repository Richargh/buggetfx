package de.richargh.buggetfx.imports.ynab.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.richargh.buggetfx.imports.ynab.asTextOrNull
import de.richargh.buggetfx.imports.ynab.model.base.*
import de.richargh.buggetfx.imports.ynab.model.entity.toYAutofillCategoryId
import de.richargh.buggetfx.imports.ynab.model.entity.*
import de.richargh.buggetfx.time.Moment
import java.io.IOException
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

class YItemDeserializer @JvmOverloads constructor(vc: Class<*>? = null): StdDeserializer<YItem>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): YItem {
        val node: JsonNode = jp.codec.readTree(jp)
        val entityType = node["entityType"].asText().toYEntityType()

        return when(entityType){
            YEntityType.PAYEE -> payee(node)
            YEntityType.TRANSACTION -> transaction(node)
            YEntityType.ACCOUNT -> account(node)
            YEntityType.MASTER_CATEGORY -> masterCategory(node)
            YEntityType.CATEGORY -> category(node)
            else              -> YItemUnknown(entityType)
        }
    }

    private fun payee(node: JsonNode): YItem {
        val autoFillMemo = node["autoFillMemo"].asTextOrNull()
        val name = node["name"].asText()
        val enabled = node["enabled"].asBoolean()
        val entityVersion = node["entityVersion"].asText().toYEntityVersion()
        val entityId = node["entityId"].asText().toYEntityId()
        val autoFillCategoryId = node["autoFillCategoryId"].asText().toYAutofillCategoryId()
        val autoFillAmount = node["autoFillAmount"].asDouble()

        return YItemPayee(
                autoFillMemo,
                name,
                enabled,
                entityVersion,
                entityId,
                autoFillCategoryId,
                autoFillAmount)
    }

    private fun transaction(node: JsonNode): YItem {

        val date = parseMoment(node["date"].asText())
        val entityId = node["entityId"].asText().toYEntityId()
        val categoryId = node["categoryId"].asText().toYCategoryId()
        val targetAccountId = node["targetAccountId"].asTextOrNull()?.toYAccountId()
        val payeeId = node["payeeId"].asText().toYPayeeId()
        val entityVersion = node["entityVersion"].asText().toYEntityVersion()
        val amount = node["amount"].asDouble()
        val accountId = node["accountId"].asText().toYAccountId()
        val memo = node["memo"].asTextOrNull()

        return YItemTransaction(
               date,
               entityId,
               categoryId,
               targetAccountId,
               payeeId,
               entityVersion,
               amount,
               accountId,
               memo)
    }

    private fun account(node: JsonNode): YItem {
        val accountName = node["accountName"].asText()

        return YAccount(
                accountName)
    }

    private fun masterCategory(node: JsonNode): YItem {
        val name = node["name"].asText()

        return YMasterCategory(
                name)
    }

    private fun category(node: JsonNode): YItem {
        val name = node["name"].asText()

        return YCategory(
                name)
    }
}

private val DATE_TIME_FORMATTER = DateTimeFormatterBuilder()
        .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd")) // 2013-05-04
        .toFormatter()

private fun parseMoment(date: String): Moment{
    val localDate = LocalDate.parse(date, DATE_TIME_FORMATTER)
    return Moment(
            ZonedDateTime.of(localDate, LocalTime.of(0, 0, 0), ZoneOffset.UTC))
}