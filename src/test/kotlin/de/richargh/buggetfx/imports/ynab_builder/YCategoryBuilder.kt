package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.model.base.toYEntityId
import de.richargh.buggetfx.imports.ynab.model.base.toYEntityVersion
import de.richargh.buggetfx.imports.ynab.model.item.YCategory
import de.richargh.buggetfx.imports.ynab.model.item.YCategoryType

class YCategoryBuilder {

    private var entityVersion = "A-5".toYEntityVersion()

    private var masterCategoryId = "A15".toYEntityId()
    private var entityId = "A16".toYEntityId()
    private var type = YCategoryType.OUTFLOW

    private var name = "Rent/Mortgage"

    fun build() = YCategory(
            entityVersion,
            masterCategoryId,
            entityId,
            type,
            name)

    fun withMasterCategoryId(masterCategoryId: String) = apply {
        this.masterCategoryId = masterCategoryId.toYEntityId()
    }
}

fun yCategory(init: YCategoryBuilder.() -> Unit): YCategory {
    val builder = YCategoryBuilder()
    builder.init()
    return builder.build()
}
