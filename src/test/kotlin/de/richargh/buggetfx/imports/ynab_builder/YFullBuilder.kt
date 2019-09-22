package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.model.budget.YFull
import de.richargh.buggetfx.imports.ynab.model.entity.YTransaction

class YFullBuilder {

    private var fileMetaData = YFileMetaDataBuilder().build()
    private var budgetMetaData = YBudgetMetaDataBuilder().build()
    private var transactions = mutableListOf<YTransaction>()

    fun build() = YFull(
            fileMetaData,
            budgetMetaData,
            transactions)

    fun withTransaction(transaction: YTransaction) = apply {
        this.transactions.add(transaction)
    }
}