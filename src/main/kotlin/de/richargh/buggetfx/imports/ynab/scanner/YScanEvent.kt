package de.richargh.buggetfx.imports.ynab.scanner

import de.richargh.buggetfx.shared_kernel.SealedEnum

class YScanEvent(
        private val eventSeverity: YScanEventSeverity,
        private val scanTarget: YScanTarget,
        private val message: String) {
    override fun toString(): String {
        return "[$eventSeverity] While scanning for [$scanTarget] this happened [$message]"
    }
}

sealed class YScanEventSeverity: SealedEnum() {
    object WARN: YScanEventSeverity()
    object ERROR: YScanEventSeverity()
}

sealed class YScanTarget: SealedEnum() {
    object DEVICE: YScanTarget()
    object FULL: YScanTarget()
    object DIFF: YScanTarget()
}

fun warn(scanTarget: YScanTarget, message: String) = YScanEvent(YScanEventSeverity.WARN, scanTarget, message)
fun error(scanTarget: YScanTarget, message: String) = YScanEvent(YScanEventSeverity.ERROR, scanTarget, message)