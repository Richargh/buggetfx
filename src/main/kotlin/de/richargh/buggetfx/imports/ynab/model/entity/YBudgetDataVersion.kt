package de.richargh.buggetfx.imports.ynab.model.entity

data class YBudgetDataVersion(private val rawValue: String)

fun String.toYBudgetDataVersion() = YBudgetDataVersion(this)
