package de.richargh.buggetfx.imports.ynab.model_builder

import de.richargh.buggetfx.imports.ynab.model.base.toYEntityVersion
import de.richargh.buggetfx.imports.ynab.model.item.YFileMetaData
import de.richargh.buggetfx.imports.ynab.model.item.toYBudgetDataVersion
import de.richargh.buggetfx.imports.ynab.model.item.toYEntityVersions

class YFileMetaDataBuilder {

    private var currentKnowledge = listOf("A-5".toYEntityVersion(), "B-2".toYEntityVersion()).toYEntityVersions()

    fun build() = YFileMetaData(
            "4.2".toYBudgetDataVersion(),
            currentKnowledge)
}
