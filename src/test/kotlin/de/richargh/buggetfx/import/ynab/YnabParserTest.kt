package de.richargh.buggetfx.import.ynab

import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import java.net.URL

class YnabParserTest {
    @Test
    fun `import bla`() {
        // arrange
        val uri = this::class.java.getResource("Simple.ydiff").toURI()
        val ynabParser = YnabParser()

        // act
        val actual = ynabParser.parse(uri)

        // assert
        val expected = YdiffBuilder().build()
        actual shouldEqual expected
    }
}