package de.richargh.buggetfx.imports.ynab.model.entity

import de.richargh.buggetfx.imports.ynab.model.base.YEntityId
import de.richargh.buggetfx.imports.ynab.model.base.YEntityVersion

data class YCategory(
        val entityVersion: YEntityVersion,

        val masterCategoryId: YEntityId,
        val entityId: YEntityId,
        val type: YCategoryType,

        val name: String): YItem