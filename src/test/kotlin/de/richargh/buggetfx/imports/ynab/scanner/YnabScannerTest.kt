package de.richargh.buggetfx.imports.ynab.scanner

import de.richargh.buggetfx.imports.ynab.mapper.YnabMapper
import de.richargh.buggetfx.imports.ynab.model.root.toYDeviceGuid
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

class YnabScannerTest {

    private val scannerResources = "src/test/resources/de/richargh/buggetfx/imports/ynab/scanner"

    @DisplayName("Example1")
    @Nested
    inner class Example1 {

        private val example1Resource = "$scannerResources/example1"

        @Test
        fun `can find all example budgets`() {
            // arrange
            val file = File("$example1Resource")
            val ynabScanner = YScanner(YnabMapper())

            // act
            val yBudgets = ynabScanner.scan(file)

            // assert
            assertThat(yBudgets.size).isEqualTo(1)
        }

        @Test
        fun `can find all devices in the example budgets`() {
            // arrange
            val file = File("$example1Resource")
            val ynabScanner = YScanner(YnabMapper())

            // act
            val yBudgets = ynabScanner.scan(file)

            // assert
            assertThat(yBudgets.single().devices.size).isEqualTo(2)
        }

        @Test
        fun `can find all device data in the example budgets`() {
            // arrange
            val file = File("$example1Resource")
            val ynabScanner = YScanner(YnabMapper())

            // act
            val yBudgets = ynabScanner.scan(file)

            // assert

            val dataA = yBudgets.single().devices.getValue("7ED5BBEA-8B0F-0C0C-A811-233A7893803B".toYDeviceGuid())
            val dataB = yBudgets.single().devices.getValue("071EF167-2726-C91B-8315-DBFFD2DF2D31".toYDeviceGuid())
            assertThat(dataA.diff.size).isEqualTo(1)
            assertThat(dataB.diff.size).isEqualTo(0)
        }
    }
}


