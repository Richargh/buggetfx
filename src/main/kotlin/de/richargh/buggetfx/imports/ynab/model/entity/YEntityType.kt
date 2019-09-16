package de.richargh.buggetfx.imports.ynab.model.entity

import de.richargh.buggetfx.shared_kernel.SealedEnum

sealed class YEntityType: SealedEnum() {
    object PAYEE: YEntityType()
    object TRANSACTION: YEntityType()
    object BUDGET_META_DATA: YEntityType()
    object ACCOUNT: YEntityType()
    object MASTER_CATEGORY: YEntityType()
    object CATEGORY: YEntityType()

    data class UNKNOWN(val rawValue: String): YEntityType()
}

fun String.toYEntityType() = when (this) {
    "payee"      -> YEntityType.PAYEE
    "transaction" -> YEntityType.TRANSACTION
    "budgetMetaData"      -> YEntityType.BUDGET_META_DATA
    "account"    -> YEntityType.ACCOUNT
    "masterCategory"    -> YEntityType.MASTER_CATEGORY
    "category"  -> YEntityType.CATEGORY
    else            -> YEntityType.UNKNOWN(this)
}
