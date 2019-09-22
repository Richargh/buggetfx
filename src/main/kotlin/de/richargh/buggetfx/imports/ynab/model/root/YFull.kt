package de.richargh.buggetfx.imports.ynab.model.root

import de.richargh.buggetfx.imports.ynab.model.item.YAccount
import de.richargh.buggetfx.imports.ynab.model.item.YBudgetMetaData
import de.richargh.buggetfx.imports.ynab.model.item.YFileMetaData
import de.richargh.buggetfx.imports.ynab.model.item.YTransaction

data class YFull(
        val fileMetaData: YFileMetaData,
        val budgetMetaData: YBudgetMetaData,
        val accounts: List<YAccount>,
        val transactions: List<YTransaction>)
