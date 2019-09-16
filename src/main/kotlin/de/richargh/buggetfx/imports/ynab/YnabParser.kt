package de.richargh.buggetfx.imports.ynab

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File
import com.fasterxml.jackson.databind.module.SimpleModule
import de.richargh.buggetfx.imports.ynab.budget.Yfull
import de.richargh.buggetfx.imports.ynab.deserializer.YItemDeserializer
import de.richargh.buggetfx.imports.ynab.diff.YItem
import de.richargh.buggetfx.imports.ynab.diff.Ydiff
import de.richargh.buggetfx.time.Moment

class YnabParser {

    private val mapper = jacksonObjectMapper().apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val module = SimpleModule()
        module.addDeserializer(MomentDeserializer())
        module.addDeserializer(YItemDeserializer())
        registerModule(module)
    }

    fun parseDiff(file: File): Ydiff = mapper.readValue(file)
    fun parseFull(file: File): Yfull = mapper.readValue(file)
}
