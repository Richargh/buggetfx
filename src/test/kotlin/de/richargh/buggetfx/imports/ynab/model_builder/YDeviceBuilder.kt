package de.richargh.buggetfx.imports.ynab.model_builder

import de.richargh.buggetfx.imports.ynab.model.base.YEntityVersion
import de.richargh.buggetfx.imports.ynab.model.base.toYEntityVersion
import de.richargh.buggetfx.imports.ynab.model.item.YEntityVersions
import de.richargh.buggetfx.imports.ynab.model.root.YDevice
import de.richargh.buggetfx.imports.ynab.model.root.toYDeviceGuid
import de.richargh.buggetfx.imports.ynab.model.root.toYShortDeviceId

class YDeviceBuilder {

    private var shortDeviceId = "foo".toYShortDeviceId()
    private var deviceGuid = "foo".toYDeviceGuid()
    private var friendlyName = "foo"
    private var deviceType = "Desktop"

    private var knowledge = listOf<YEntityVersion>()
    private var knowledgeInFullBudgetFile = listOf<YEntityVersion>()
    private var hasFullKnowledge = true

    fun build() = YDevice(
            shortDeviceId,
            deviceGuid,
            friendlyName,
            deviceType,

            YEntityVersions(knowledge),
            YEntityVersions(knowledgeInFullBudgetFile),
            hasFullKnowledge)

    fun shortDeviceId(shortDeviceId: String) {
        this.shortDeviceId = shortDeviceId.toYShortDeviceId()
    }

    fun deviceGuid(deviceGuid: String) {
        this.deviceGuid = deviceGuid.toYDeviceGuid()
    }

    fun friendlyName(friendlyName: String) {
        this.friendlyName = friendlyName
    }

    fun deviceType(deviceType: String) {
        this.deviceType = deviceType
    }

    fun knowledge(vararg entityVersions: String) {
        this.knowledge = entityVersions.map { it.toYEntityVersion() }
    }

    fun knowledgeInFullBudgetFile(vararg entityVersions: String) {
        this.knowledgeInFullBudgetFile = entityVersions.map { it.toYEntityVersion() }
    }

    fun noKnowledgeInFullBudgetFile() {
        this.knowledgeInFullBudgetFile = emptyList()
    }

    fun hasFullKnowledge() {
        this.hasFullKnowledge = true
    }

    fun noFullKnowledge() {
        this.hasFullKnowledge = false
    }
}

fun yDevice(init: YDeviceBuilder.() -> Unit): YDevice {
    val builder = YDeviceBuilder()
    builder.init()
    return builder.build()
}

fun YDeviceBuilder.presetPc() {
    shortDeviceId("A")
    deviceGuid("7ED5BBEA-8B0F-0C0C-A811-233A7893803B")
    friendlyName("PC")
    deviceType("Desktop (AIR), OS:Windows 8")

    knowledge("A-5", "B-2")
    knowledgeInFullBudgetFile("A-4", "B-2")
    hasFullKnowledge()
}

fun YDeviceBuilder.presetPhone() {
    shortDeviceId("B")
    deviceGuid("071EF167-2726-C91B-8315-DBFFD2DF2D31")
    friendlyName("Overpriced iPhone")
    deviceType("iPhone")

    knowledge("A-5", "B-2")
    noKnowledgeInFullBudgetFile()
    noFullKnowledge()
}