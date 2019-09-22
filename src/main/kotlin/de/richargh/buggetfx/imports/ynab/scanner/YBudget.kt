package de.richargh.buggetfx.imports.ynab.scanner

import de.richargh.buggetfx.imports.ynab.model.root.YDevice

class YBudget(
        val scanEvents: List<YScanEvent>,
        val name: String,
        val path: String,

        val devices: List<YDevice>)