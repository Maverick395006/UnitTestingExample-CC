package com.maverick.unittestingexample_cc

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.gson.JsonSyntaxException
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException

class QuoteManagerTest {

    private lateinit var quoteManager: QuoteManager
    private lateinit var context: Context

    @Before
    fun setUp() {
        //Arrange
        quoteManager = QuoteManager()
        context = ApplicationProvider.getApplicationContext()

        quoteManager.populateQuotes(
            arrayOf(
                Quote("This is FIRST Quote", "First"),
                Quote("This is SECOND Quote", "Second"),
                Quote("This is THIRD Quote", "Third")
            )
        )
    }

    @Test(expected = FileNotFoundException::class)
    fun populateQuotesFromAssets_inputBlankFileName_returnsFileNotFoundException() {
        //Act
        quoteManager.populateQuotesFromAssets(context, "")
    }

    @Test(expected = FileNotFoundException::class)
    fun populateQuotesFromAssets_inputNonExistingFileName_returnsFileNotFoundException() {
        //Act
        quoteManager.populateQuotesFromAssets(context, "cdac.json")
    }

    @Test(expected = JsonSyntaxException::class)
    fun populateQuotesFromAssets__inputErrorJsonFileName_returnsJsonSyntaxException() {
        //Act
        quoteManager.populateQuotesFromAssets(context, "quotesinvalid.json")
    }

    @Test
    fun populateQuotesFromAssets__inputValidFileName_returnsTrue() {
        //Act
        quoteManager.populateQuotesFromAssets(context, "quotes.json")
        //Assert
        assertEquals(1643, quoteManager.quoteList.size)
    }

    @Test
    fun testPreviousQuote_Expected_Correct_Quote() {
        //Act
        val quote = quoteManager.getPreviousQuote()
        //Assert
        assertEquals("First", quote.author)
    }

    @Test
    fun testNextQuote_Expected_Correct_Quote() {
        //Act
        val quote = quoteManager.getNextQuote()
        //Assert
        assertEquals("Second", quote.author)
    }

}