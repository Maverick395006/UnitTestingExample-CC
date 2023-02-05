package com.maverick.unittestingexample_cc.repository

import com.maverick.unittestingexample_cc.api.ProductAPI
import com.maverick.unittestingexample_cc.models.ProductListItem
import com.maverick.unittestingexample_cc.utils.NetworkResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository(private val productAPI: ProductAPI) {

    suspend fun getProducts(): Flow<NetworkResult<List<ProductListItem>>> = flow {

        emit(NetworkResult.Loading)
        delay(2000)

        try {
            val response = productAPI.getProducts()
            emit(NetworkResult.Success(response))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e))
        }
    }

}
