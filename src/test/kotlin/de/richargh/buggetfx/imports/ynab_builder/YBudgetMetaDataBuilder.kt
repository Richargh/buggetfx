package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.model.base.toYEntityVersion
import de.richargh.buggetfx.imports.ynab.model.entity.YBudgetMetaData

class YBudgetMetaDataBuilder {

    fun build() = YBudgetMetaData(
            "A-5".toYEntityVersion())

}
