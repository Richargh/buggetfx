package de.richargh.buggetfx.imports.ynab.model_builder

import de.richargh.buggetfx.imports.ynab.model.item.YItem
import de.richargh.buggetfx.imports.ynab.model.root.YDiff
import de.richargh.buggetfx.time.Cronus

class YDiffBuilder {

    private var shortDeviceId = "A"
    private var dataVersion = "4.2"
    private var publishTime = Cronus.of(2019, 9, 8, 12, 56, 8)

    private var startVersion = "A-4,B-2"
    private var endVersion = "A-5,B-2"

    private val items = mutableListOf<YItem>()

    fun build(): YDiff {
        return YDiff(
                shortDeviceId,
                dataVersion,
                publishTime,

                startVersion,
                endVersion,
                items)
    }

    fun plusItem(item: YItem) = apply { items += item }
    operator fun YItem.unaryPlus() { items.add(this) }
}

fun yDiff(init: YDiffBuilder.() -> Unit): YDiff {
    val builder = YDiffBuilder()
    builder.init()
    return builder.build()
}