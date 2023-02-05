package com.maverick.unittestingexample_cc.api

import com.maverick.unittestingexample_cc.models.ProductListItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {

    @GET("/products")
    suspend fun getProducts() : List<ProductListItem>

}