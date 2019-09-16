package de.richargh.buggetfx.imports.ynab.model.base

data class YCategoryId(val rawValue: String)

fun String.toYCategoryId() = YCategoryId(this)