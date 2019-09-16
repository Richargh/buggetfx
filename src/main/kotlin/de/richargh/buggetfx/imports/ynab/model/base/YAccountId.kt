package de.richargh.buggetfx.imports.ynab.model.base

data class YAccountId(val rawValue: String)

fun String.toYAccountId() = YAccountId(this)