package de.richargh.buggetfx.imports.ynab.model.diff

data class YAutofillCategoryId(val rawValue: String)

fun String.toYAutofillCategoryId() = YAutofillCategoryId(this)