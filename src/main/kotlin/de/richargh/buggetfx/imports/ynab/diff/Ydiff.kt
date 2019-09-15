package de.richargh.buggetfx.imports.ynab.diff

import de.richargh.buggetfx.time.Moment

data class Ydiff(
        val shortDeviceId: String,
        val dataVersion: String,
        val publishTime: Moment,

        val startVersion: String,
        val endVersion: String)