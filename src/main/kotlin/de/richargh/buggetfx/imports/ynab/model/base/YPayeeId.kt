package de.richargh.buggetfx.imports.ynab.model.base

data class YPayeeId(val rawValue: String)

fun String.toYPayeeId() = YPayeeId(this)