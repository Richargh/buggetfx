package de.richargh.buggetfx.import.ynab

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File
import com.fasterxml.jackson.databind.module.SimpleModule
import de.richargh.buggetfx.time.Moment

class YnabParser {

    private val mapper = jacksonObjectMapper().apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val module = SimpleModule()
        module.addDeserializer(Moment::class.java, MomentDeserializer())
        registerModule(module)
    }

    fun parse(file: File): Ydiff = mapper.readValue(file)
}


