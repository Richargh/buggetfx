package de.richargh.buggetfx.imports.ynab.model.entity

import de.richargh.buggetfx.imports.ynab.model.base.YEntityId
import de.richargh.buggetfx.imports.ynab.model.base.YEntityVersion

data class YItemPayee(
        val autoFillMemo: String?,
        val name: String,
        val enabled: Boolean,
        val entityVersion: YEntityVersion,
        val entityId: YEntityId,
        val autoFillCategoryId: YAutofillCategoryId,
        val autoFillAmount: Double): YItem {
    override val entityType: YEntityType = YEntityType.PAYEE
}