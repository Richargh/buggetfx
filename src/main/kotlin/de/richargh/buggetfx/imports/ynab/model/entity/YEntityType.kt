package de.richargh.buggetfx.imports.ynab.model.entity

import de.richargh.buggetfx.shared_kernel.SealedEnum

sealed class YEntityType: SealedEnum() {
    object ACCOUNT: YEntityType()
    object BUDGET_META_DATA: YEntityType()
    object CATEGORY: YEntityType()
    object MASTER_CATEGORY: YEntityType()
    object MATCHED_TRANSACTION: YEntityType()
    object PAYEE: YEntityType()
    object TRANSACTION: YEntityType()

    data class UNKNOWN(val rawValue: String): YEntityType()
}

fun String.toYEntityType() = when (this) {
    "account"    -> YEntityType.ACCOUNT
    "budgetMetaData"      -> YEntityType.BUDGET_META_DATA
    "category"  -> YEntityType.CATEGORY
    "masterCategory"    -> YEntityType.MASTER_CATEGORY
    "matchedTransaction" -> YEntityType.MATCHED_TRANSACTION
    "payee"      -> YEntityType.PAYEE
    "transaction" -> YEntityType.TRANSACTION
    else            -> YEntityType.UNKNOWN(this)
}
