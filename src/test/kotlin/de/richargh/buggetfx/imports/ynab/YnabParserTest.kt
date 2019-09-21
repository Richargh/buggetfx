package de.richargh.buggetfx.imports.ynab

import de.richargh.buggetfx.imports.ynab_builder.*
import org.amshove.kluent.shouldEqual
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

class YnabParserTest {

    @Nested
    @DisplayName("YDiff")
    inner class Diff {

        @Test
        fun `account diff should match default account diff`() {
            // arrange
            val file = File(this::class.java.getResource("Account.ydiff").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val expected = YAccountBuilder().build()
            val expectedDiff = YDiffBuilder().plusItem(expected).build()
            actualDiff shouldEqual expectedDiff
        }

        @Test
        fun `budget meta data diff should match default budget meta data diff`() {
            // arrange
            val file = File(this::class.java.getResource("BudgetMetaData.ydiff").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val expected = YBudgetMetaDataBuilder().build()
            val expectedDiff = YDiffBuilder().plusItem(expected).build()
            actualDiff shouldEqual expectedDiff
        }

        @Test
        fun `category diff should match default category diff`() {
            // arrange
            val file = File(this::class.java.getResource("Category.ydiff").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val expected = YCategoryBuilder().build()
            val expectedDiff = YDiffBuilder().plusItem(expected).build()
            actualDiff shouldEqual expectedDiff
        }

        @Test
        fun `master category diff should match default master category diff`() {
            // arrange
            val file = File(this::class.java.getResource("MasterCategory.ydiff").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val expected = YMasterCategoryBuilder().build()
            val expectedDiff = YDiffBuilder().plusItem(expected).build()
            actualDiff shouldEqual expectedDiff
        }

        @Test
        fun `matched transaction diff should match default matched transaction diff`() {
            // arrange
            val file = File(this::class.java.getResource("MatchedTransaction.ydiff").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val expected = YItemMatchedTransactionBuilder().build()
            val expectedTransaction = YItemTransactionBuilder().plusMatchedTransaction(expected).build()
            val expectedDiff = YDiffBuilder().plusItem(expectedTransaction).build()
            assertThat(actualDiff).isEqualTo(expectedDiff)
        }


        @Test
        fun `payee diff should match default payee diff`() {
            // arrange
            val file = File(this::class.java.getResource("Payee.ydiff").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val expected = YItemPayeeBuilder().build()
            val expectedDiff = YDiffBuilder().plusItem(expected).build()
            actualDiff shouldEqual expectedDiff
        }

        @Test
        fun `transaction diff should match default transaction diff`() {
            // arrange
            val file = File(this::class.java.getResource("Transaction.ydiff").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val expected = YItemTransactionBuilder().build()
            val expectedDiff = YDiffBuilder().plusItem(expected).build()
            actualDiff shouldEqual expectedDiff
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