package de.richargh.buggetfx.imports.ynab

import de.richargh.buggetfx.imports.ynab_builder.YdiffBuilder
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import java.io.File

class YnabParserTest {
    @Test
    fun `import bla`() {
        // arrange
        val file = File(this::class.java.getResource("Simple.ydiff").file)
        val ynabParser = YnabParser()

        // act
        val actual = ynabParser.parse(file)

        // assert
        val expected = YdiffBuilder().build()
        actual shouldEqual expected
    }
}