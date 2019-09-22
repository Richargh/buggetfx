package de.richargh.buggetfx.imports.ynab.model.root

import de.richargh.buggetfx.imports.ynab.model.entity.YItem
import de.richargh.buggetfx.time.Moment

data class YDiff(
        val shortDeviceId: String,
        val dataVersion: String,
        val publishTime: Moment,

        val startVersion: String,
        val endVersion: String,

        val items: List<YItem>)