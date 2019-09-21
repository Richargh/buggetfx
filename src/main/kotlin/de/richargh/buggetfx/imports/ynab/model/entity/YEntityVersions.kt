package de.richargh.buggetfx.imports.ynab.model.entity

import de.richargh.buggetfx.imports.ynab.model.base.YEntityVersion

data class YEntityVersions(val versions: List<YEntityVersion>)

fun List<YEntityVersion>.toYEntityVersions() = YEntityVersions(this)