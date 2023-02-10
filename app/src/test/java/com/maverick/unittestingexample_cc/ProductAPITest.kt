package com.maverick.unittestingexample_cc

import com.maverick.unittestingexample_cc.api.ProductAPI
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductAPITest {

    lateinit var mockWebServer: MockWebServer
    lateinit var productAPI: ProductAPI

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        productAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ProductAPI::class.java)
    }

    @Test
    fun testGetProducts() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = productAPI.getProducts()
        mockWebServer.takeRequest()
        assertEquals(true, response.body()!!.isEmpty())
    }

    @Test
    fun testGetProducts_returnProducts() = runTest {
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = productAPI.getProducts()
        mockWebServer.takeRequest()
        assertEquals(true, response.body()!!.isNotEmpty())
        assertEquals(2, response.body()!!.size)
    }

    @Test
    fun testGetProducts_returnError() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something went wrong")
        mockWebServer.enqueue(mockResponse)

        val response = productAPI.getProducts()
        mockWebServer.takeRequest()
        assertEquals(false, response.isSuccessful)
        assertEquals(404, response.code())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}