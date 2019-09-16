package de.richargh.buggetfx.imports.ynab.model.entity

data class YAutofillCategoryId(val rawValue: String)

fun String.toYAutofillCategoryId() = YAutofillCategoryId(this)