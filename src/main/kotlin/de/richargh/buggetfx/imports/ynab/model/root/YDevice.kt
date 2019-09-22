package de.richargh.buggetfx.imports.ynab.model.root

import de.richargh.buggetfx.imports.ynab.model.base.YEntityVersion
import de.richargh.buggetfx.imports.ynab.model.item.YEntityVersions

data class YDevice(
        val shortDeviceId: YShortDeviceId,
        val deviceGUID: YDeviceGuid,
        val friendlyName: String,
        val deviceType: String,

        val knowledge: YEntityVersions,
        val knowledgeInFullBudgetFile: YEntityVersions,
        val hasFullKnowledge: Boolean)