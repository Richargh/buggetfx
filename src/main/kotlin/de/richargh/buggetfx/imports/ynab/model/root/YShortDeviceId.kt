package de.richargh.buggetfx.imports.ynab.model.root

import de.richargh.buggetfx.imports.ynab.model.base.YAccountId

data class YShortDeviceId(private val rawValue: String)

fun String.toYShortDeviceId() = YShortDeviceId(this)