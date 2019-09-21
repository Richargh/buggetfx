package de.richargh.buggetfx.imports.ynab.model.budget

import de.richargh.buggetfx.imports.ynab.model.entity.YFileMetaData

data class YFull(
        val fileMetaData: YFileMetaData,
        val transactions: List<YTransaction>)
