package de.richargh.buggetfx.imports.ynab.model.budget

import de.richargh.buggetfx.imports.ynab.model.entity.YBudgetMetaData
import de.richargh.buggetfx.imports.ynab.model.entity.YFileMetaData
import de.richargh.buggetfx.imports.ynab.model.entity.YTransaction

data class YFull(
        val fileMetaData: YFileMetaData,
        val budgetMetaData: YBudgetMetaData,
        val transactions: List<YTransaction>)
