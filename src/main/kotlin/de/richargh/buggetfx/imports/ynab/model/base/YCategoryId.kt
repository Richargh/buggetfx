package de.richargh.buggetfx.imports.ynab.model.base

data class YCategoryId(val rawValue: String){
    companion object{
        val SPLIT = YCategoryId("Category/__Split__")
        val DEFERRED_INCOME = YCategoryId("Category/__DeferredIncome__")
        val IMMEDIATE_INCOME = YCategoryId("Category/__ImmediateIncome__")
    }
}

fun String.toYCategoryId() = YCategoryId(this)