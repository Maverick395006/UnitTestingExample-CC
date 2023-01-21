package com.maverick.unittestingexample_cc.utils

import org.junit.Assert.*
import org.junit.Test

class ReversalStringTest {

    @Test
    fun testStringReversal_EmptyString_returnsEmptyString() {
        val sut = Helper()
        val result = sut.reverseString("")
        assertEquals("", result)
    }

    @Test
    fun testStringReversal_SingleChar_returnsSingleChar() {
        val sut = Helper()
        val result = sut.reverseString("p")
        assertEquals("p", result)
    }

    @Test
    fun testStringReversal_ValidInput_returnsValidInput() {
        val sut = Helper()
        val result = sut.reverseString("abc")
        assertEquals("cba", result)
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun testStringReversal_NullInput_returnsIllegalArgumentException() {
        val sut = Helper()
        sut.reverseString(null)
    }

}