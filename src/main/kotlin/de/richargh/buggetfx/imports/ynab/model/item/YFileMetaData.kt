package de.richargh.buggetfx.imports.ynab.model.item

data class YFileMetaData(
        val budgetDataVersion: YBudgetDataVersion,
        val currentKnowledge: YEntityVersions): YItem {
    override val entityType get() = YEntityType.BUDGET_META_DATA
}