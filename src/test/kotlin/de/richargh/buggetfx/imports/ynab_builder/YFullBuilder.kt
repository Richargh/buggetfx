package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.budget.YTransaction
import de.richargh.buggetfx.imports.ynab.budget.YFull

class YFullBuilder {

    private var transactions: List<YTransaction> = emptyList()

    fun build() = YFull(transactions)

}