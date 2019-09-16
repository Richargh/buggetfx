package de.richargh.buggetfx.imports.ynab.model.base

data class YEntityId(val rawValue: String)

fun String.toYEntityId() = YEntityId(this)