package de.richargh.buggetfx.import.ynab

class YdiffBuilder {

    private var shortDeviceId = "A"
    private var dataVersion = "4.2"

    private var startVersion = "A-4,B-2"
    private var endVersion = "A-5,B-2"

    fun build(): Ydiff {
        return Ydiff(
                shortDeviceId,
                dataVersion,

                startVersion,
                endVersion)
    }
}