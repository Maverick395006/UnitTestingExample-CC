package com.maverick.unittestingexample_cc.repository

import android.util.Log
import com.google.gson.Gson
import com.maverick.unittestingexample_cc.api.ProductAPI
import com.maverick.unittestingexample_cc.api.RemoteMapper
import com.maverick.unittestingexample_cc.db.CacheMapper
import com.maverick.unittestingexample_cc.db.ProductDao
import com.maverick.unittestingexample_cc.models.Product
import com.maverick.unittestingexample_cc.utils.NetworkResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository(
    private val productAPI: ProductAPI,
    private val productDao: ProductDao,
    private val remoteMapper: RemoteMapper,
    private val cacheMapper: CacheMapper
) {

    suspend fun getProducts(): Flow<NetworkResult<List<Product>>> = flow {

        emit(NetworkResult.Loading)
        delay(2000)

        try {
            // Fetch Data From Server/Remote
            val remoteData = productAPI.getProducts()
            Log.e("API Response", Gson().toJson(remoteData))

            // Convert Remote entity -> domain entity
            val productData :List<Product> = remoteMapper.mapFromEntityList(remoteData)
            Log.e("Mapped Response", productData.toString())

            // Add domain entity data -> Local DB with mapper.
            for (product in productData) {
                productDao.insert(cacheMapper.mapToEntity(product))
            }

            // get data from Local DB
            val cacheProduct = productDao.get()
            Log.e("DB Response", cacheProduct.toString())

            // Convert Local DB entity -> domain entity
            val mProductData :List<Product> = cacheMapper.mapFromEntityList(cacheProduct)

            // Success State: Finally emit data  as Single Source of truth
            emit(NetworkResult.Success(mProductData))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e))
        }
    }

}
