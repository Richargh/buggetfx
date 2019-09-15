package de.richargh.buggetfx.import.ynab

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File

inline fun <reified T> ObjectMapper.readValue(file: File) = readValue(file, T::class.java)