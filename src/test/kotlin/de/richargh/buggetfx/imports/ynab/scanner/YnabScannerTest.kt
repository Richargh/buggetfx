package de.richargh.buggetfx.imports.ynab.scanner

import de.richargh.buggetfx.imports.ynab.mapper.YnabMapper
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
    }
}


