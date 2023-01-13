package com.maverick.unittestingexample_cc.utils

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class HelperTest {

    lateinit var helper: Helper

    @Before
    fun setUp() {
        //Arrange
        helper = Helper()
    }

    @Test
    fun `isPalindrome Expect False`() {
        //Act
        val result = helper.isPalindrome("abc")
        //Assert
        assertEquals(false,result)
    }

    @Test
    fun `isPalindrome Expect True`() {
        //Act
        val result = helper.isPalindrome("oyo")
        //Assert
        assertEquals(true,result)
    }
}