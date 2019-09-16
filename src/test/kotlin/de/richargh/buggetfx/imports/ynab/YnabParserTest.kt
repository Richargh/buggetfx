package de.richargh.buggetfx.imports.ynab

import de.richargh.buggetfx.imports.ynab_builder.YItemPayeeBuilder
import de.richargh.buggetfx.imports.ynab_builder.YFullBuilder
import de.richargh.buggetfx.imports.ynab_builder.YDiffBuilder
import de.richargh.buggetfx.imports.ynab_builder.YItemTransactionBuilder
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

class YnabParserTest {

    @Nested
    @DisplayName("YDiff")
    inner class Diff {
        @Test
        fun `payee diff should match default payee diff`() {
            // arrange
            val file = File(this::class.java.getResource("Payee.ydiff").file)
            val ynabParser = YnabParser()

            // act
            val actual = ynabParser.parseDiff(file)

            // assert
            val expectedAutofill = YItemPayeeBuilder().build()
            val expected = YDiffBuilder().plusItem(expectedAutofill).build()
            actual shouldEqual expected
        }

        @Test
        fun `transaction diff should match default transaction diff`() {
            // arrange
            val file = File(this::class.java.getResource("Transaction.ydiff").file)
            val ynabParser = YnabParser()

            // act
            val actual = ynabParser.parseDiff(file)

            // assert
            val expectedAutofill = YItemTransactionBuilder().build()
            val expected = YDiffBuilder().plusItem(expectedAutofill).build()
            actual shouldEqual expected
        }
    }


    @Nested
    @DisplayName("YFull")
    inner class Full {

        @Test
        fun `empty budget should match empty budget`() {
            // arrange
            val file = File(this::class.java.getResource("Empty.yfull").file)
            val ynabParser = YnabParser()

            // act
            val actual = ynabParser.parseFull(file)

            // assert
            val expected = YFullBuilder().build()
            actual shouldEqual expected
        }
    }
}