package de.richargh.buggetfx.imports.ynab.model.root

import de.richargh.buggetfx.imports.ynab.model.item.*

data class YFull(
        val fileMetaData: YFileMetaData,
        val budgetMetaData: YBudgetMetaData,
        val accounts: List<YAccount>,
        val masterCategories: List<YMasterCategory>,
        val payees: List<YPayee>,
        val transactions: List<YTransaction>)
