package com.maverick.unittestingexample_cc.repository

import com.maverick.unittestingexample_cc.api.ProductAPI
import com.maverick.unittestingexample_cc.models.ProductListItem
import com.maverick.unittestingexample_cc.utils.NetworkResult

class ProductRepository(private val productAPI: ProductAPI) {

    suspend fun getProducts(): NetworkResult<List<ProductListItem>> {
        val response = productAPI.getProducts()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkResult.Success(responseBody)
            } else {
                NetworkResult.Error("Something went wrong")
            }
        } else {
            NetworkResult.Error("Something went wrong")
        }
    }

}
