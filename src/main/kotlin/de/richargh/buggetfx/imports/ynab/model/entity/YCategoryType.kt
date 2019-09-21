package de.richargh.buggetfx.imports.ynab.model.entity

import de.richargh.buggetfx.shared_kernel.SealedEnum

sealed class YCategoryType: SealedEnum() {
    object OUTFLOW: YCategoryType()
    object INFLOW: YCategoryType()
}

fun String.toYCategoryType() = when (this) {
    "OUTFLOW" -> YCategoryType.OUTFLOW
    "INFLOW"  -> YCategoryType.INFLOW
    // default is also outflow
    else      -> YCategoryType.OUTFLOW
}