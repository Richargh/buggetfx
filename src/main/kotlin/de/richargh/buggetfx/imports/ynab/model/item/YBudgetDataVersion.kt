package de.richargh.buggetfx.imports.ynab.model.item

data class YBudgetDataVersion(private val rawValue: String)

fun String.toYBudgetDataVersion() = YBudgetDataVersion(this)
