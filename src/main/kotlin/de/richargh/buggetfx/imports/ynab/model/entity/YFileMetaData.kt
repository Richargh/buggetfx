package de.richargh.buggetfx.imports.ynab.model.entity

data class YFileMetaData(
        val budgetDataVersion: YBudgetDataVersion,
        val currentKnowledge: YEntityVersions): YItem {
    override val entityType get() = YEntityType.BUDGET_META_DATA
}