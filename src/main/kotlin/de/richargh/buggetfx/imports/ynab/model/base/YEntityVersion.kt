package de.richargh.buggetfx.imports.ynab.model.base

data class YEntityVersion(private val rawValue: String)

fun String.toYEntityVersion() = YEntityVersion(this)
