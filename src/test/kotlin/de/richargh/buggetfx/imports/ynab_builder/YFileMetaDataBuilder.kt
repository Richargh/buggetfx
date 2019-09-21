package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.model.entity.YFileMetaData
import de.richargh.buggetfx.imports.ynab.model.entity.toYBudgetDataVersion

class YFileMetaDataBuilder {

    fun build() = YFileMetaData(
            "4.2".toYBudgetDataVersion())
}