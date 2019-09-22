package de.richargh.buggetfx.imports.ynab.scanner

import de.richargh.buggetfx.imports.ynab.mapper.YnabMapper
import de.richargh.buggetfx.imports.ynab.model.root.YDevice
import de.richargh.buggetfx.imports.ynab.model.root.YDeviceGuid
import de.richargh.buggetfx.imports.ynab.model.root.YDiff
import de.richargh.buggetfx.imports.ynab.model.root.toYDeviceGuid
import java.io.File

class YScanner(val mapper: YnabMapper) {
    fun scan(file: File): List<YBudget> {
        return YScanContext(mapper).walkBudgets(file)
    }
}

private class YScanContext(val mapper: YnabMapper) {
    private val scanEvents = mutableListOf<YScanEvent>()

    fun walkBudgets(file: File): List<YBudget> {
        val budgets = file.walk().asSequence().filter {
            it.isYnabBudgetFolder()
        }.map { walkBudget(it) }.toList()

        return budgets
    }

    private fun walkBudget(budgetFolder: File): YBudget {
        val dataDevices = walkDeviceData(budgetFolder)
        return YBudget(
                scanEvents,
                budgetFolder.name,
                budgetFolder.path,

                dataDevices)
    }

    private fun walkDeviceData(budgetFolder: File): Map<YDeviceGuid, YDataDevice> {
        val deviceFolders = budgetFolder.walk().filter {
            it.isYnabDeviceFolder()
        }.toList()
        val devices: Map<YDeviceGuid, YDevice> = scanDeviceDeclarations(deviceFolders, budgetFolder)

        return budgetFolder.walk().filter {
            it.isDirectory && devices.containsKey(it.name.toYDeviceGuid())
        }.map {
            val device = devices.getValue(it.name.toYDeviceGuid())
            YDataDevice(device, walkDiff(it))
        }.associate { it.device.deviceGUID to it }
    }

    private fun walkDiff(deviceFolder: File): List<YDiff> {
        return deviceFolder.walk().filter {
            it.isYnabDiffFile()
        }.map {
            mapper.toDiff(it)
        }.toList()
    }

    private fun scanDeviceDeclarations(deviceFolders: List<File>,
                                       budgetFolder: File): Map<YDeviceGuid, YDevice> {
        return if (deviceFolders.isEmpty()) {
            scanEvents.add(noDeviceError(budgetFolder));
            emptyMap()
        } else {
            val deviceFolder = deviceFolders.first()
            if (deviceFolders.size > 1)
                scanEvents.add(tooManyDevicesWarn(budgetFolder, deviceFolders.size, deviceFolder));
            walkDeviceDeclarations(deviceFolder)
        }
    }

    private fun walkDeviceDeclarations(deviceDeclarationFolder: File): Map<YDeviceGuid, YDevice> {
        val devices = deviceDeclarationFolder.walk().filter {
            it.isYnabDeviceFile()
        }.associate {
            mapper.toDevice(it).let { device -> device.deviceGUID to device }
        }

        return devices
    }

    private fun File.isYnabBudgetFolder() = this.isDirectory && this.name.endsWith(".ynab4")

    private fun File.isYnabDeviceFolder() = this.isDirectory && this.name == "devices"
    private fun File.isYnabDeviceFile() = this.isFile && this.name.endsWith(".ydevice")

    private fun File.isYnabDiffFile() = this.isFile && this.name.endsWith(".ydiff")
}

private fun noDeviceError(budgetFolder: File) =
        error(YScanTarget.DEVICE, "We could not find a single devicefolder inside [${budgetFolder.absolutePath}]")

private fun tooManyDevicesWarn(budgetFolder: File, deviceFolderSize: Int, deviceFolder: File) = warn(
        YScanTarget.DEVICE,
        "We found [${deviceFolderSize}] devicefolders inside [${budgetFolder.absolutePath}] and will only pick the first one [${deviceFolder.name}].")