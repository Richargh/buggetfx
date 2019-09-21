package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.model.budget.YTransaction
import de.richargh.buggetfx.imports.ynab.model.budget.YFull

class YFullBuilder {

    private var fileMetaData = YFileMetaDataBuilder().build()
    private var transactions: List<YTransaction> = emptyList()

    fun build() = YFull(
            fileMetaData,
            transactions)

}