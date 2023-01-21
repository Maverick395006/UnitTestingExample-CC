package com.maverick.unittestingexample_cc.utils

import org.junit.Assert
import org.junit.Test

class ValidPasswordTest {

    @Test
    fun validatePassword_BlankInput_ReturnsRequiredField() {
        val sut = Helper()
        val result = sut.validatePassword("    ")
        Assert.assertEquals("Password is required Field", result)
    }

    @Test
    fun validatePassword_LessInput_ReturnsTooShort() {
        val sut = Helper()
        val result = sut.validatePassword("abc0")
        Assert.assertEquals("Password is too Short", result)
    }

    @Test
    fun validatePassword_ExtraInput_ReturnsTooLong() {
        val sut = Helper()
        val result = sut.validatePassword("abcdefghijklmnopqrstuvwxyz0")
        Assert.assertEquals("Password is too Long", result)
    }

    @Test
    fun validatePassword_ValidInput_ReturnsValidate() {
        val sut = Helper()
        val result = sut.validatePassword("maverick001")
        Assert.assertEquals("Valid", result)
    }

}