package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.model.base.*
import de.richargh.buggetfx.imports.ynab.model.item.YMatchedTransaction
import de.richargh.buggetfx.time.Cronus

class YMatchedTransactionBuilder {

    fun build() = YMatchedTransaction(
            Cronus.of(2018, 7, 6),
            "97F314EA-91FB-19AB-8396-CE0004ADDC20/MatchedTransaction/Transaction/YNAB:-30,00:2019-02-28:1".toYEntityId(),
            "3A471795-F8F2-7727-F36C-D0D1A4E8C4F9".toYCategoryId(),
            null,
            "C45B535E-4AAD-C2FD-7A91-46F1AE68A6ED".toYPayeeId(),
            "A-2".toYEntityVersion(),
            -104.19,
            "C8847157-E05B-49FA-86AA-5D60556A5E9D".toYAccountId(),
            "maybe for the Horde")
}

fun yMatchedTransaction(init: YMatchedTransactionBuilder.() -> Unit): YMatchedTransaction {
    val builder = YMatchedTransactionBuilder()
    builder.init()
    return builder.build()
}
