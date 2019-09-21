package de.richargh.buggetfx.imports.ynab.model.entity

import de.richargh.buggetfx.shared_kernel.SealedEnum

sealed class YAccountType: SealedEnum(){
    object CASH: YAccountType()
    object LINE_OF_CREDIT: YAccountType()
    object CREDIT_CARD: YAccountType()
    object CHECKING: YAccountType()

    data class UNKNOWN(val rawValue: String): YAccountType()
}

fun String.toYAccountType() = when (this) {
    "Cash"    -> YAccountType.CASH
    "LineofCredit"    -> YAccountType.LINE_OF_CREDIT
    "CreditCard"    -> YAccountType.CREDIT_CARD
    "Checking"    -> YAccountType.CHECKING
    else            -> YAccountType.UNKNOWN(this)
}