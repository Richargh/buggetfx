package de.richargh.buggetfx.imports.ynab.model.item

import de.richargh.buggetfx.imports.ynab.model.base.YEntityId
import de.richargh.buggetfx.imports.ynab.model.base.YEntityVersion

data class YPayee(
        val autoFillMemo: String?,
        val name: String,
        val entityVersion: YEntityVersion,
        val entityId: YEntityId,
        val autoFillCategoryId: YAutofillCategoryId,
        val autoFillAmount: Double): YItem {
    override val entityType: YEntityType = YEntityType.PAYEE
}