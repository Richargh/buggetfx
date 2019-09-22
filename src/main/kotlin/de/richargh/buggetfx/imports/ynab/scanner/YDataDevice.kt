package de.richargh.buggetfx.imports.ynab.scanner

import de.richargh.buggetfx.imports.ynab.model.root.YDevice
import de.richargh.buggetfx.imports.ynab.model.root.YDiff

class YDataDevice(val device: YDevice,
                  val diff: List<YDiff>)