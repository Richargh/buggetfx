package de.richargh.buggetfx.imports.ynab.scanner

import de.richargh.buggetfx.imports.ynab.mapper.YnabMapper
import de.richargh.buggetfx.imports.ynab.model.root.*
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

    private fun walkDeviceData(budgetFolder: File): Map<YDeviceGuid, YDataForDevice> {
        val deviceFolders = budgetFolder.walk().filter {
            it.isYnabDeviceFolder()
        }.toList()
        val devices: Map<YDeviceGuid, YDevice> = scanDeviceDeclarations(deviceFolders, budgetFolder)

        return budgetFolder.walk().filter {
            it.isDirectory && devices.containsKey(it.name.toYDeviceGuid())
        }.map {
            val device = devices.getValue(it.name.toYDeviceGuid())
            YDataForDevice(device, walkFull(it), walkDiff(it))
        }.associateBy { it.device.deviceGUID }
    }

    private fun walkFull(deviceFolder: File): YFull? {
        val fullFiles = deviceFolder.walk().filter {
            it.isYnabFullFile()
        }.toList()
        return if (fullFiles.isEmpty()) {
            scanEvents.add(noFull(deviceFolder))
            null
        } else {
            val fullFile = fullFiles.first()
            if (fullFiles.size > 1)
                scanEvents.add(tooManyFullWarn(deviceFolder, fullFiles.size, fullFile))
            mapper.toFull(fullFile)
        }
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
        }.map {
            mapper.toDevice(it)
        }.associateBy { it.deviceGUID }

        return devices
    }

    private fun File.isYnabBudgetFolder() = this.isDirectory && this.name.endsWith(".ynab4")

    private fun File.isYnabDeviceFolder() = this.isDirectory && this.name == "devices"
    private fun File.isYnabDeviceFile() = this.isFile && this.name.endsWith(".ydevice")

    private fun File.isYnabFullFile() = this.isFile && this.name.endsWith(".yfull")
    private fun File.isYnabDiffFile() = this.isFile && this.name.endsWith(".ydiff")
}

private fun noFull(deviceDataFolder: File) =
        error(YScanTarget.FULL, "Could not find a single .yfull inside [${deviceDataFolder.absolutePath}]")

private fun tooManyFullWarn(deviceDataFolder: File, fullFileSize: Int, fullFile: File) =
        warn(YScanTarget.FULL,
             "Found [${fullFileSize}] .yfull files inside [${deviceDataFolder.absolutePath}] and will only pick the first one [${fullFile.name}].")

private fun noDeviceError(budgetFolder: File) =
        error(YScanTarget.DEVICE, "Could not find a single devicefolder inside [${budgetFolder.absolutePath}]")

private fun tooManyDevicesWarn(budgetFolder: File, deviceFolderSize: Int, deviceFolder: File) = warn(
        YScanTarget.DEVICE,
        "Found [${deviceFolderSize}] devicefolders inside [${budgetFolder.absolutePath}] and will only pick the first one [${deviceFolder.name}].")