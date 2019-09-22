package de.richargh.buggetfx.imports.ynab

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File
import com.fasterxml.jackson.databind.module.SimpleModule
import de.richargh.buggetfx.imports.ynab.deserializer.*
import de.richargh.buggetfx.imports.ynab.model.root.YDevice
import de.richargh.buggetfx.imports.ynab.model.root.YFull
import de.richargh.buggetfx.imports.ynab.model.root.YDiff

class YnabMapper {

    private val mapper = jacksonObjectMapper().apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val module = SimpleModule()
        module.addDeserializer(MomentDeserializer())
        module.addDeserializer(YEntityVersionsDeserializer())
        module.addDeserializer(YEntityTypeDeserializer())

        module.addDeserializer(YItemDeserializer())
        module.addDeserializer(YAccountDeserializer())
        module.addDeserializer(YBudgetMetaDataDeserializer())
        module.addDeserializer(YCategoryDeserializer())
        module.addDeserializer(YMasterCategoryDeserializer())
        module.addDeserializer(YMatchedTransactionDeserializer())
        module.addDeserializer(YPayeeDeserializer())
        module.addDeserializer(YSubTransactionDeserializer())
        module.addDeserializer(YTransactionDeserializer())

        module.addDeserializer(YDeviceDeserializer())

        registerModule(module)
    }

    fun toDiff(file: File): YDiff = mapper.readValue(file)
    fun toFull(file: File): YFull = mapper.readValue(file)
    fun toDevice(file: File): YDevice = mapper.readValue(file)
}
