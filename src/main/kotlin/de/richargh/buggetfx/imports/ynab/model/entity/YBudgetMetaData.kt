package de.richargh.buggetfx.imports.ynab.model.entity

import de.richargh.buggetfx.imports.ynab.model.base.YEntityVersion

data class YBudgetMetaData(
        val entityVersion: YEntityVersion) : YItem{
    override val entityType: YEntityType = YEntityType.BUDGET_META_DATA
}