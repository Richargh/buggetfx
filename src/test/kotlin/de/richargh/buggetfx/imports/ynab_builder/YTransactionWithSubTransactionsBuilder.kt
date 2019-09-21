package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.model.base.*
import de.richargh.buggetfx.imports.ynab.model.entity.YSubTransaction
import de.richargh.buggetfx.imports.ynab.model.entity.YTransaction
import de.richargh.buggetfx.time.Cronus

class YTransactionWithSubTransactionsBuilder {

    private var entityId = "97F314EA-91FB-19AB-8396-CE0004ADDC20".toYEntityId()
    private var subTransactions = mutableListOf<YSubTransaction>()

    fun build(): YTransaction {
        return YTransaction(
                Cronus.of(2018, 7, 6),
                entityId,
                YCategoryId.SPLIT,
                null,
                "C45B535E-4AAD-C2FD-7A91-46F1AE68A6ED".toYPayeeId(),
                "A-5".toYEntityVersion(),
                -104.19,
                "C8847157-E05B-49FA-86AA-5D60556A5E9D".toYAccountId(),
                "for the Horde",
                emptyList(),
                subTransactions)
    }

    fun plusSubTransactions(vararg subTransactions: YSubTransaction) = apply {
        subTransactions.forEach { this.subTransactions.add(it) }
    }

    fun withEntityId(entityId: String) = apply { this.entityId = entityId.toYEntityId() }
}
