package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.model.budget.YFull
import de.richargh.buggetfx.imports.ynab.model.entity.YAccount
import de.richargh.buggetfx.imports.ynab.model.entity.YTransaction

class YFullBuilder {

    private var fileMetaData = YFileMetaDataBuilder().build()
    private var budgetMetaData = YBudgetMetaDataBuilder().build()

    private var accounts = mutableListOf<YAccount>()
    private var transactions = mutableListOf<YTransaction>()

    fun build() = YFull(
            fileMetaData,
            budgetMetaData,
            accounts,
            transactions)

    fun plusTransaction(transaction: YTransaction) = apply {
        this.transactions.add(transaction)
    }

    fun plusAccount(account: YAccount) = apply {
        this.accounts.add(account)
    }
}

fun yFull(init: YFullBuilder.() -> Unit): YFull {
    val builder = YFullBuilder()
    builder.init()
    return builder.build()
}