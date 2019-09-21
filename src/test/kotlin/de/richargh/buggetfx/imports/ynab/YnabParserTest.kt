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
        fun `sub transaction diff should match default sub transaction diff`() {
            // arrange
            val file = File(this::class.java.getResource("SubTransaction.ydiff").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val parentTransactionId = "97F314EA-91FB-19AB-8396-CE0004ADDC20"
            val expectedA4 = YItemSubTransactionBuilder()
                    .withVersion("A-4")
                    .withParentTransactionId(parentTransactionId)
                    .withEntityId("06EE8FB8-B340-BB11-322A-EDF9B29856BE")
                    .withCategoryId("C5B7787D-85C6-CB4C-DC7F-D0D3559DF05C")
                    .withAmount(-100.00)
                    .withMemo("Horde!")
                    .build()
            val expectedA5 = YItemSubTransactionBuilder()
                    .withVersion("A-5")
                    .withParentTransactionId(parentTransactionId)
                    .withEntityId("820CE5CF-A970-9B97-8446-EDF9B298BA5F")
                    .withCategoryId("2E45C5BA-06BC-17F4-08CF-D0E31EE589D5")
                    .withAmount(-4.19)
                    .withoutMemo()
                    .build()
            val expectedTransaction = YItemTransactionWithSubTransactionsBuilder()
                    .withEntityId(parentTransactionId)
                    .plusSubTransactions(expectedA4, expectedA5)
                    .build()
            val expectedDiff = YDiffBuilder().plusItem(expectedTransaction).build()
            assertThat(actualDiff).isEqualTo(expectedDiff)
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