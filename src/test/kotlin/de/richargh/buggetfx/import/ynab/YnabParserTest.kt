package de.richargh.buggetfx.import.ynab

import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import java.io.File
import java.net.URL

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