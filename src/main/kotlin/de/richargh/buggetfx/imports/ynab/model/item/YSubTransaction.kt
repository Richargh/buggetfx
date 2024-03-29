package de.richargh.buggetfx.imports.ynab.model.item

import de.richargh.buggetfx.imports.ynab.model.base.YCategoryId
import de.richargh.buggetfx.imports.ynab.model.base.YEntityId
import de.richargh.buggetfx.imports.ynab.model.base.YEntityVersion

data class YSubTransaction(
        val parentTransactionId: YEntityId,
        val entityId: YEntityId,
        val categoryId: YCategoryId,
        val entityVersion: YEntityVersion,
        val amount: Double,
        val memo: String?): YItem {

    override val entityType: YEntityType = YEntityType.SUB_TRANSACTION
}