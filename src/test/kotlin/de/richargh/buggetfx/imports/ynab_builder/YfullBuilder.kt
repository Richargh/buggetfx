package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.budget.YTransaction
import de.richargh.buggetfx.imports.ynab.budget.Yfull

class YfullBuilder {

    private var transactions: List<YTransaction> = emptyList()

    fun build() = Yfull(transactions)

}