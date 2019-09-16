package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.diff.YItem
import de.richargh.buggetfx.imports.ynab.diff.Ydiff
import de.richargh.buggetfx.time.Cronus

class YdiffBuilder {

    private var shortDeviceId = "A"
    private var dataVersion = "4.2"
    private var publishTime = Cronus.of(2019, 9, 8, 12, 56, 8)

    private var startVersion = "A-4,B-2"
    private var endVersion = "A-5,B-2"

    private val items = mutableListOf<YItem>()

    fun build(): Ydiff {
        return Ydiff(
                shortDeviceId,
                dataVersion,
                publishTime,

                startVersion,
                endVersion,
                items)
    }

    fun plusItem(item: YItem) = apply { items += item }
}