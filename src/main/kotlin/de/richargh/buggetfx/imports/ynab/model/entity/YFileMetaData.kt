package de.richargh.buggetfx.imports.ynab.model.entity

data class YFileMetaData(val budgetDataVersion: YBudgetDataVersion): YItem {
    override val entityType get() = YEntityType.BUDGET_META_DATA
}