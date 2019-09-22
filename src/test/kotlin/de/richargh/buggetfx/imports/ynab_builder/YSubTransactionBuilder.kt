package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.model.base.*
import de.richargh.buggetfx.imports.ynab.model.item.YSubTransaction

class YSubTransactionBuilder {

    private var parentTransactionId = "bla".toYEntityId()
    private var entityId = "foo".toYEntityId()
    private var categoryId = "nope".toYCategoryId()
    private var entityVersion = "A-2".toYEntityVersion()
    private var amount = -104.19
    private var memo: String? = "maybe for the Horde"

    fun build(): YSubTransaction {
        return YSubTransaction(
                parentTransactionId,
                entityId,
                categoryId,
                entityVersion,
                amount,
                memo)
    }

    fun withVersion(entityVersion: String) = apply { this.entityVersion = entityVersion.toYEntityVersion() }
    fun withParentTransactionId(parentTransactionId: String) = apply { this.parentTransactionId = parentTransactionId.toYEntityId() }
    fun withEntityId(entityId: String) = apply { this.entityId = entityId.toYEntityId() }
    fun withCategoryId(categoryId: String) = apply { this.categoryId = categoryId.toYCategoryId() }
    fun withAmount(amount: Double) = apply { this.amount = amount }
    fun withMemo(memo: String) = apply { this.memo = memo }
    fun withoutMemo() = apply { this.memo = null }
}
