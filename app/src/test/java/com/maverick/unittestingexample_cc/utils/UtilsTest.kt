package com.maverick.unittestingexample_cc.utils

import com.maverick.unittestingexample_cc.MainCoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UtilsTest {

    // // we have define StandardTestDispatcher to execute all coroutines in single thread
    // // Cause: We don't need different Threads for unit tests
    // @OptIn(ExperimentalCoroutinesApi::class)
    // private val testDispatcher = StandardTestDispatcher()

    // @OptIn(ExperimentalCoroutinesApi::class)
    // @Before
    // fun setUp() {
    //     Dispatchers.setMain(testDispatcher)
    // }

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testUserName() {
        val sut = Utils(mainCoroutineRule.testDispatcher)
        runTest {
            sut.getUser()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testAddressDetail() {
        val sut = Utils(mainCoroutineRule.testDispatcher)
        runTest {
            sut.getAddressDetail()
            // below c-line for execute all coroutines before assertion
            mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(true, sut.globalArg)
        }
    }

    // @OptIn(ExperimentalCoroutinesApi::class)
    // @After
    // fun tearDown() {
    //     Dispatchers.resetMain()
    // }

}