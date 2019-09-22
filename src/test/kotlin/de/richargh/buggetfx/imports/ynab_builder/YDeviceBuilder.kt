package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.model.root.YDevice
import de.richargh.buggetfx.imports.ynab.model.root.toYShortDeviceId

class YDeviceBuilder {

    private var shortDeviceId = "foo".toYShortDeviceId()

    fun build() = YDevice(
            shortDeviceId)

    fun shortDeviceId(shortDeviceId: String) {
        this.shortDeviceId = shortDeviceId.toYShortDeviceId()
    }
}

fun yDevice(init: YDeviceBuilder.() -> Unit): YDevice {
    val builder = YDeviceBuilder()
    builder.init()
    return builder.build()
}

fun YDeviceBuilder.presetPc() {
    shortDeviceId("A")
}