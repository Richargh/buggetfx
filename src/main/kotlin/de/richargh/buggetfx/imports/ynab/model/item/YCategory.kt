package de.richargh.buggetfx.imports.ynab.model.item

import de.richargh.buggetfx.imports.ynab.model.base.YEntityId
import de.richargh.buggetfx.imports.ynab.model.base.YEntityVersion

data class YCategory(
        val entityVersion: YEntityVersion,

        val masterCategoryId: YEntityId,
        val entityId: YEntityId,
        val type: YCategoryType,

        val name: String): YItem{

    override val entityType = YEntityType.CATEGORY
}