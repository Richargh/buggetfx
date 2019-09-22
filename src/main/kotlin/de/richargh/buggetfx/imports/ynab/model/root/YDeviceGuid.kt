package de.richargh.buggetfx.imports.ynab.model.root

import de.richargh.buggetfx.imports.ynab.model.base.YAccountId

data class YDeviceGuid(private val rawValue: String)

fun String.toYDeviceGuid() = YDeviceGuid(this)