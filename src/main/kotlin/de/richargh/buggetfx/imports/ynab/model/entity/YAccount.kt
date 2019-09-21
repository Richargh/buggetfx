package de.richargh.buggetfx.imports.ynab.model.entity

import de.richargh.buggetfx.imports.ynab.model.base.YEntityId
import de.richargh.buggetfx.imports.ynab.model.base.YEntityVersion

data class YAccount(
        val entityVersion: YEntityVersion,

        val entityId: YEntityId,
        val accountType: YAccountType,

        val accountName: String,
        val lastReconciledBalance: Double) : YItem{
    override val entityType: YEntityType = YEntityType.ACCOUNT
}