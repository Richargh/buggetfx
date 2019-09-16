package de.richargh.buggetfx.imports.ynab.model.base

import de.richargh.buggetfx.shared_kernel.SealedEnum

sealed class YEntityType: SealedEnum() {
    object PAYEE: YEntityType()
    object TRANSACTION: YEntityType()
    object BUDGET_META_DATA: YEntityType()
    object ACCOUNT: YEntityType()
    object MASTER_CATEGORY: YEntityType()
    object CATEGORY: YEntityType()

    object UNKNOWN: YEntityType()
}

fun String.toYEntityType() = when (this) {
    "LOKFAHRT"      -> YEntityType.PAYEE
    "FIKTIVE_FAHRT" -> YEntityType.TRANSACTION
    "ZUGFAHRT"      -> YEntityType.BUDGET_META_DATA
    "FAEHRFAHRT"    -> YEntityType.ACCOUNT
    "SPERRFAHRT"    -> YEntityType.MASTER_CATEGORY
    "RANGIERFAHRT"  -> YEntityType.CATEGORY
    else            -> YEntityType.UNKNOWN
}
