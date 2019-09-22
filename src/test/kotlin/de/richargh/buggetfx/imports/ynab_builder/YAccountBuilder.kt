package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.model.base.toYEntityId
import de.richargh.buggetfx.imports.ynab.model.base.toYEntityVersion
import de.richargh.buggetfx.imports.ynab.model.entity.YAccount
import de.richargh.buggetfx.imports.ynab.model.entity.YAccountType

class YAccountBuilder {

    private var entityVersion = "A-5".toYEntityVersion()

    private var entityId = "9B4EC170-16ED-C13C-B5DB-2C3FB66E900A".toYEntityId()
    private var accountType = YAccountType.CASH
    private var accountName = "Best Bank"

    private var lastReconciledBalance = 1632.99

    fun build() = YAccount(
            entityVersion,
            entityId,
            accountType,
            accountName,
            lastReconciledBalance)
}

fun yAccount(init: YAccountBuilder.() -> Unit): YAccount {
    val builder = YAccountBuilder()
    builder.init()
    return builder.build()
}