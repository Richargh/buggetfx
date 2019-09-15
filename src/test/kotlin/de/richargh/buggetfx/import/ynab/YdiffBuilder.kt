package de.richargh.buggetfx.import.ynab

class YdiffBuilder {

    private var shortDeviceId = "O"

    fun build(): Ydiff {
        return Ydiff(shortDeviceId)
    }
}