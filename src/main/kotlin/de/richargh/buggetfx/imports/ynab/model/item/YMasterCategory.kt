package de.richargh.buggetfx.imports.ynab.model.item

import de.richargh.buggetfx.imports.ynab.model.base.YEntityId
import de.richargh.buggetfx.imports.ynab.model.base.YEntityVersion

data class YMasterCategory(
        val entityVersion: YEntityVersion,
        val entityId: YEntityId,
        val type: YCategoryType,
        val name: String,
        val subCategories: List<YCategory>): YItem{
    override val entityType: YEntityType = YEntityType.MASTER_CATEGORY
}