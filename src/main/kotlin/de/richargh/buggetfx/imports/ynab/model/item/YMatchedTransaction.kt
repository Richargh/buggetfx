package de.richargh.buggetfx.imports.ynab.model.item

import de.richargh.buggetfx.imports.ynab.model.base.*
import de.richargh.buggetfx.time.Moment

data class YMatchedTransaction(
    val date: Moment,
    val entityId: YEntityId,
    val categoryId: YCategoryId,
    val targetAccountId: YAccountId?,
    val payeeId: YPayeeId,
    val entityVersion: YEntityVersion,
    val amount: Double,
    val accountId: YAccountId,
    val memo: String?): YItem{

    override val entityType: YEntityType = YEntityType.MATCHED_TRANSACTION
}