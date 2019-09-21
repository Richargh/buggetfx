package de.richargh.buggetfx.imports.ynab

import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import de.richargh.buggetfx.time.Moment
import java.io.File

inline fun <reified T> ObjectMapper.readValue(file: File) = readValue(file, T::class.java)

inline fun <reified T> SimpleModule.addDeserializer(deserializer: JsonDeserializer<T>) = addDeserializer(T::class.java, deserializer)

fun JsonNode?.asTextOrNull() = if(this == null || this.isNull){ null } else { this.asText() }
fun JsonNode?.asBooleanOrNull() = if(this == null || this.isNull){ null } else { this.asBoolean() }
fun JsonNode?.asDoubleOrNull() = if(this == null || this.isNull){ null } else { this.asDouble() }