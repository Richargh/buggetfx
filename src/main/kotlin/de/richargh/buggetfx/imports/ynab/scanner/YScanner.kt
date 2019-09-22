package de.richargh.buggetfx.imports.ynab.scanner

import de.richargh.buggetfx.imports.ynab.mapper.YnabMapper
import de.richargh.buggetfx.imports.ynab.model.root.YDevice
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
        val deviceFolders = budgetFolder.walk().filter {
            it.isYnabDeviceFolder()
        }.toList()
        val devices: List<YDevice> = if (deviceFolders.isEmpty()) {
            scanEvents.add(noDeviceError(budgetFolder));
            emptyList()
        } else {
            val deviceFolder = deviceFolders.first()
            if (deviceFolders.size > 1)
                scanEvents.add(tooManyDevicesWarn(budgetFolder, deviceFolders.size, deviceFolder));
            walkDeviceDeclarations(deviceFolder)
        }
        return YBudget(
                scanEvents,
                budgetFolder.name,
                budgetFolder.path,

                devices)
    }

    private fun walkDiff(deviceFolder: File): List<YDevice> {
        val devices = deviceFolder.walk().filter {
            it.isYnabDeviceFile()
        }.map {
            mapper.toDevice(it)
        }.toList()

        return devices
    }

    private fun walkDeviceDeclarations(deviceDeclarationFolder: File): List<YDevice> {
        val devices = deviceDeclarationFolder.walk().filter {
            it.isYnabDeviceFile()
        }.map {
            mapper.toDevice(it)
        }.toList()

        return devices
    }

    private fun File.isYnabBudgetFolder() = this.isDirectory && this.name.endsWith(".ynab4")

    private fun File.isYnabDeviceFolder() = this.isDirectory && this.name == "devices"
    private fun File.isYnabDeviceFile() = this.isFile && this.name.endsWith(".ydevice")
}

private fun noDeviceError(budgetFolder: File) =
        error(YScanTarget.DEVICE, "We could not find a single devicefolder inside [${budgetFolder.absolutePath}]")

private fun tooManyDevicesWarn(budgetFolder: File, deviceFolderSize: Int, deviceFolder: File) = warn(
        YScanTarget.DEVICE,
        "We found [${deviceFolderSize}] devicefolders inside [${budgetFolder.absolutePath}] and will only pick the first one [${deviceFolder.name}].")