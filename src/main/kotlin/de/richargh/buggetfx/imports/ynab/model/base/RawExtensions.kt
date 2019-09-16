package de.richargh.buggetfx.imports.ynab.model.base

fun String.toYEntityId() = YEntityId(this)
fun String.toYEntityType() = YEntityType(this)
fun String.toYEntityVersion() = YEntityVersion(this)
