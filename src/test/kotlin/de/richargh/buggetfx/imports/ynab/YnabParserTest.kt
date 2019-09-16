package de.richargh.buggetfx.imports.ynab

import de.richargh.buggetfx.imports.ynab_builder.YItemPayeeBuilder
import de.richargh.buggetfx.imports.ynab_builder.YFullBuilder
import de.richargh.buggetfx.imports.ynab_builder.YDiffBuilder
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import java.io.File

class YnabParserTest {
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