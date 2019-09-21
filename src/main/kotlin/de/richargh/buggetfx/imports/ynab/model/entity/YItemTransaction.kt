package de.richargh.buggetfx.imports.ynab.model.entity

import de.richargh.buggetfx.imports.ynab.model.base.*
import de.richargh.buggetfx.time.Moment

data class YItemTransaction(
    val date: Moment,
    val entityId: YEntityId,
    val categoryId: YCategoryId,
    val targetAccountId: YAccountId?,
    val payeeId: YPayeeId,
    val entityVersion: YEntityVersion,
    val amount: Double,
    val accountId: YAccountId,
    val memo: String?,
    val matchedTransactions: List<YItemMatchedTransaction>,
    val subTransactions: List<YItemSubTransaction>): YItem{

    val entityType: YEntityType = YEntityType.TRANSACTION
}

val SPLIT_CATEGORY = YCategoryId("Category/__Split__")