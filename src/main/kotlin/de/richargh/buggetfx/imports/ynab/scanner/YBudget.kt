package de.richargh.buggetfx.imports.ynab.scanner

import de.richargh.buggetfx.imports.ynab.model.root.YDeviceGuid

class YBudget(
        val scanEvents: List<YScanEvent>,
        val name: String,
        val path: String,

        val devices: Map<YDeviceGuid, YDataForDevice>)