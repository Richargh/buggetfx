package de.richargh.buggetfx.imports.ynab.model.item

data class YAutofillCategoryId(val rawValue: String)

fun String.toYAutofillCategoryId() = YAutofillCategoryId(this)