package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.model.base.toYEntityVersion
import de.richargh.buggetfx.imports.ynab.model.entity.YAccount
import de.richargh.buggetfx.imports.ynab.model.entity.YBudgetMetaData

class YBudgetMetaDataBuilder {

    fun build() = YBudgetMetaData(
            "A-0".toYEntityVersion())

}

fun yBudgetMetaData(init: YBudgetMetaDataBuilder.() -> Unit): YBudgetMetaData {
    val builder = YBudgetMetaDataBuilder()
    builder.init()
    return builder.build()
}