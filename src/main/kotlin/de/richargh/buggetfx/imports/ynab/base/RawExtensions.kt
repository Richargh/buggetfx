package de.richargh.buggetfx.imports.ynab.base

import de.richargh.buggetfx.imports.ynab.base.YEntityId
import de.richargh.buggetfx.imports.ynab.base.YEntityType
import de.richargh.buggetfx.imports.ynab.base.YEntityVersion

fun String.toYEntityId() = YEntityId(this)
fun String.toYEntityType() = YEntityType(this)
fun String.toYEntityVersion() = YEntityVersion(this)
