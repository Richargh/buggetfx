package de.richargh.buggetfx.imports.ynab.model.entity

import de.richargh.buggetfx.imports.ynab.model.base.YEntityId
import de.richargh.buggetfx.imports.ynab.model.base.YEntityVersion
import de.richargh.buggetfx.imports.ynab.model.base.YEntityType
import de.richargh.buggetfx.imports.ynab.model.diff.YAutofillCategoryId

data class YItemAutofill(
        val autoFillMemo: String,
        val name: String,
        val enabled: Boolean,
        val entityVersion: YEntityVersion,
        val entityId: YEntityId,
        val autoFillCategoryId: YAutofillCategoryId,
        val entityType: YEntityType,
        val autoFillAmount: Double): YItem