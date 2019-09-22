package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.model.base.toYEntityId
import de.richargh.buggetfx.imports.ynab.model.base.toYEntityVersion
import de.richargh.buggetfx.imports.ynab.model.item.YCategory
import de.richargh.buggetfx.imports.ynab.model.item.YCategoryType
import de.richargh.buggetfx.imports.ynab.model.item.YMasterCategory

class YMasterCategoryBuilder {

    private var entityVersion = "A-4".toYEntityVersion()

    private var entityId = "F9C3D722".toYEntityId()
    private var type = YCategoryType.OUTFLOW
    private var name = "Quarterly Bills"
    private var subCategories = mutableListOf<YCategory>()

    fun build(): YMasterCategory {
        return YMasterCategory(
                entityVersion,
                entityId,
                type,
                name,
                subCategories)
    }

    fun withEntityId(entityId: String) = apply { this.entityId = entityId.toYEntityId() }

    fun plusSubCategory(category: YCategory) = apply {
        subCategories.add(category)
    }
}

fun yMasterCategory(init: YMasterCategoryBuilder.() -> Unit): YMasterCategory {
    val builder = YMasterCategoryBuilder()
    builder.init()
    return builder.build()
}