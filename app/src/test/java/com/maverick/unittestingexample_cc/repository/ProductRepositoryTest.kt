package com.maverick.unittestingexample_cc.repository

import com.maverick.unittestingexample_cc.api.ProductAPI
import com.maverick.unittestingexample_cc.models.ProductListItem
import com.maverick.unittestingexample_cc.utils.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class ProductRepositoryTest {

    @Mock
    lateinit var productAPI: ProductAPI

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetProducts_EmptyList() = runTest {
        Mockito.`when`(productAPI.getProducts()).thenReturn(Response.success(emptyList()))

        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        assertEquals(true, result is NetworkResult.Success)
        assertEquals(0, result.data!!.size)
    }

    @Test
    fun testGetProducts_expectProductList() = runTest {
        val productList = listOf(
            ProductListItem("abc", "ABC"),
            ProductListItem("def", "DEF")
        )
        Mockito.`when`(productAPI.getProducts()).thenReturn(Response.success(productList))

        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        assertEquals(true, result is NetworkResult.Success)
        assertEquals(2, result.data!!.size)
        assertEquals("abc", result.data!![0].title)
        assertEquals("def", result.data!![1].title)
        assertEquals("ABC", result.data!![0].image)
        assertEquals("DEF", result.data!![1].image)
    }

    @Test
    fun testGetProducts_expectError() = runTest {
        Mockito.`when`(productAPI.getProducts())
            .thenReturn(Response.error(401, "Unauthorised".toResponseBody()))

        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        assertEquals(true, result is NetworkResult.Error)
        assertEquals("Something went wrong", result.message)
    }

}