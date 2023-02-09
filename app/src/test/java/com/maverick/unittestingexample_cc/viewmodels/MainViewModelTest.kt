package com.maverick.unittestingexample_cc.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.maverick.unittestingexample_cc.getOrAwaitValue
import com.maverick.unittestingexample_cc.repository.ProductRepository
import com.maverick.unittestingexample_cc.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    // It is used for create a single coroutine for all task
    private val testDispatcher = StandardTestDispatcher()

    // It will run all architecture component on its main thread
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ProductRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_GetProducts() = runTest {
        Mockito.`when`(repository.getProducts()).thenReturn(NetworkResult.Success(emptyList()))

        val sut = MainViewModel(repository)
        sut.getProducts()

        // It will Complete all scheduled tasks before Assertion.
        testDispatcher.scheduler.advanceUntilIdle()

        val result = sut.products.getOrAwaitValue()
        assertEquals(0, result.data!!.size)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}