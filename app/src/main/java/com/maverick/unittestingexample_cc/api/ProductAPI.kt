package com.maverick.unittestingexample_cc.api

import retrofit2.http.GET

interface ProductAPI {

    @GET("/products")
    suspend fun getProducts() : List<ProductRemote>

}