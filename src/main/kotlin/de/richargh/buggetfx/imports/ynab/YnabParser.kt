package de.richargh.buggetfx.imports.ynab

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File
import com.fasterxml.jackson.databind.module.SimpleModule
import de.richargh.buggetfx.imports.ynab.deserializer.MomentDeserializer
import de.richargh.buggetfx.imports.ynab.model.budget.YFull
import de.richargh.buggetfx.imports.ynab.deserializer.YItemDeserializer
import de.richargh.buggetfx.imports.ynab.model.diff.YDiff

class YnabParser {

    private val mapper = jacksonObjectMapper().apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val module = SimpleModule()
        module.addDeserializer(MomentDeserializer())
        module.addDeserializer(YItemDeserializer())
        registerModule(module)
    }

    fun parseDiff(file: File): YDiff = mapper.readValue(file)
    fun parseFull(file: File): YFull = mapper.readValue(file)
}
