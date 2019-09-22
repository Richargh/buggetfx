package de.richargh.buggetfx.imports.ynab.scanner

import de.richargh.buggetfx.imports.ynab.model.root.YDevice
import de.richargh.buggetfx.imports.ynab.model.root.YDiff
import de.richargh.buggetfx.imports.ynab.model.root.YFull

class YDataForDevice(
        val device: YDevice,
        val full: YFull?,
        val diff: List<YDiff>)