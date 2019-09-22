package de.richargh.buggetfx.imports.ynab.scanner

import de.richargh.buggetfx.imports.ynab.mapper.YnabMapper
import de.richargh.buggetfx.imports.ynab.model.root.YDevice
import java.io.File

class YScanner(val mapper: YnabMapper) {
    fun scan(file: File): List<YBudget> {
        return walkBudgets(file)
    }

    private fun walkBudgets(file: File): List<YBudget>{
        val budgets = mutableListOf<YBudget>()
        file.walk().onEnter {
            if(it.isYnabBudgetFolder()){
                budgets.add(walkBudget(it))
                false
            } else {
                true
            }
        }.forEach { /* call foreach beacuse onEnter is otherwise not called */ }

        return budgets
    }

    private fun walkBudget(budgetFolder: File): YBudget{
        var devices = emptyList<YDevice>()
        budgetFolder.walk().onEnter {
            if(it.isYnabDeviceFolder()){
                devices = walkDevices(it)
                false
            }else {
                true
            }
        }.forEach {  }

        val budget = YBudget(
                budgetFolder.name,
                budgetFolder.path,

                devices)
        return budget
    }

    private fun walkDevices(deviceFolder: File): List<YDevice>{
        val devices = deviceFolder.walk().filter {
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