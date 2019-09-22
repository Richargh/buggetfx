package de.richargh.buggetfx.imports.ynab.model_builder

import de.richargh.buggetfx.imports.ynab.model.base.YEntityId
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

    infix fun entityId(entityId: String){ entityId(entityId.toYEntityId()) }
    infix fun entityId(entityId: YEntityId){ this.entityId = entityId }

    operator fun YCategory.unaryPlus() { subCategories.add(this) }

    fun subCategory(init: YCategoryBuilder.() -> Unit): YCategory {
        val builder = YCategoryBuilder()
        builder.masterCategoryId(entityId)
        builder.init()
        return builder.build()
    }
}

fun yMasterCategory(init: YMasterCategoryBuilder.() -> Unit): YMasterCategory {
    val builder = YMasterCategoryBuilder()
    builder.init()
    return builder.build()
}