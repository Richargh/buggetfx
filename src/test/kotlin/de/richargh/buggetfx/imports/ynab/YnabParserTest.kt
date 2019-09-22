package de.richargh.buggetfx.imports.ynab

import de.richargh.buggetfx.imports.ynab_builder.*
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
            val file = File(this::class.java.getResource("Account.ydiff.json").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val expectedDiff = yDiff { +yAccount {  } }
            assertThat(actualDiff).isEqualTo(expectedDiff)
        }

        @Test
        fun `budget meta data diff should match default budget meta data diff`() {
            // arrange
            val file = File(this::class.java.getResource("BudgetMetaData.ydiff.json").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val expectedDiff = yDiff { +yBudgetMetaData {} }
            assertThat(actualDiff).isEqualTo(expectedDiff)
        }

        @Test
        fun `category diff should match default category diff`() {
            // arrange
            val file = File(this::class.java.getResource("Category.ydiff.json").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val expectedDiff = yDiff { +yCategory {  } }
            assertThat(actualDiff).isEqualTo(expectedDiff)
        }

        @Test
        fun `master category diff should match default master category diff`() {
            // arrange
            val file = File(this::class.java.getResource("MasterCategory.ydiff.json").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val masterCategoryId = "F9C3D722"
            val expectedCategory = YCategoryBuilder().withMasterCategoryId(masterCategoryId).build()
            val expectedMasterCategory = YMasterCategoryBuilder()
                    .withEntityId(masterCategoryId).plusSubCategory(expectedCategory).build()
            val expectedDiff = yDiff { +expectedMasterCategory }
            assertThat(actualDiff).isEqualTo(expectedDiff)
        }

        @Test
        fun `matched transaction diff should match default matched transaction diff`() {
            // arrange
            val file = File(this::class.java.getResource("MatchedTransaction.ydiff.json").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val expected = yMatchedTransaction { }
            val expectedTransaction = YTransactionBuilder().plusMatchedTransaction(expected).build()
            val expectedDiff = yDiff { +expectedTransaction }
            assertThat(actualDiff).isEqualTo(expectedDiff)
        }

        @Test
        fun `payee diff should match default payee diff`() {
            // arrange
            val file = File(this::class.java.getResource("Payee.ydiff.json").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val expectedDiff = yDiff { +yPayee {  } }
            assertThat(actualDiff).isEqualTo(expectedDiff)
        }

        @Test
        fun `sub transaction diff should match default sub transaction diff`() {
            // arrange
            val file = File(this::class.java.getResource("SubTransaction.ydiff.json").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val parentTransactionId = "97F314EA-91FB-19AB-8396-CE0004ADDC20"
            val expectedA4 = YSubTransactionBuilder()
                    .withVersion("A-4")
                    .withParentTransactionId(parentTransactionId)
                    .withEntityId("06EE8FB8-B340-BB11-322A-EDF9B29856BE")
                    .withCategoryId("C5B7787D-85C6-CB4C-DC7F-D0D3559DF05C")
                    .withAmount(-100.00)
                    .withMemo("Horde!")
                    .build()
            val expectedA5 = YSubTransactionBuilder()
                    .withVersion("A-5")
                    .withParentTransactionId(parentTransactionId)
                    .withEntityId("820CE5CF-A970-9B97-8446-EDF9B298BA5F")
                    .withCategoryId("2E45C5BA-06BC-17F4-08CF-D0E31EE589D5")
                    .withAmount(-4.19)
                    .withoutMemo()
                    .build()
            val expectedTransaction = YTransactionWithSubTransactionsBuilder()
                    .withEntityId(parentTransactionId)
                    .plusSubTransactions(expectedA4, expectedA5)
                    .build()
            val expectedDiff = YDiffBuilder().plusItem(expectedTransaction).build()
            assertThat(actualDiff).isEqualTo(expectedDiff)
        }

        @Test
        fun `transaction diff should match default transaction diff`() {
            // arrange
            val file = File(this::class.java.getResource("Transaction.ydiff.json").file)
            val ynabParser = YnabParser()

            // act
            val actualDiff = ynabParser.parseDiff(file)

            // assert
            val expectedDiff = yDiff { +yTransaction { } }
            assertThat(actualDiff).isEqualTo(expectedDiff)
        }
    }

    @Nested
    @DisplayName("YFull")
    inner class Full {

        @Test
        fun `empty budget should match empty budget`() {
            // arrange
            val file = File(this::class.java.getResource("Empty.yfull.json").file)
            val ynabParser = YnabParser()

            // act
            val actualFull = ynabParser.parseFull(file)

            // assert
            val expectedFull = yFull { }
            assertThat(actualFull).isEqualTo(expectedFull)
        }

        @Test
        fun `single transaction budget should match default budget`() {
            // arrange
            val file = File(this::class.java.getResource("SingleTransaction.yfull.json").file)
            val ynabParser = YnabParser()

            // act
            val actualFull = ynabParser.parseFull(file)

            // assert
            val expectedFull = yFull {
                +yAccount { }
                +yTransaction { }
            }
            assertThat(actualFull).isEqualTo(expectedFull)
        }
    }
}


