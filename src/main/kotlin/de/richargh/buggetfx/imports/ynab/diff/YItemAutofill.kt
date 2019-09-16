package de.richargh.buggetfx.imports.ynab.diff

import de.richargh.buggetfx.imports.ynab.base.YEntityId
import de.richargh.buggetfx.imports.ynab.base.YEntityVersion
import de.richargh.buggetfx.imports.ynab.base.YEntityType

data class YItemAutofill(
        val autoFillMemo: String,
        val name: String,
        val enabled: Boolean,
        val entityVersion: YEntityVersion,
        val entityId: YEntityId,
        val autoFillCategoryId: YAutofillCategoryId,
        val entityType: YEntityType,
        val autoFillAmount: Double): YItem