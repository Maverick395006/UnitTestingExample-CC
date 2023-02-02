package com.maverick.unittestingexample_cc

import android.app.Application
import com.maverick.unittestingexample_cc.api.ProductAPI
import com.maverick.unittestingexample_cc.repository.ProductRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoreApplication : Application() {

    lateinit var productAPI: ProductAPI
    lateinit var productRepository: ProductRepository

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fakestoreapi.com/")
            .build()

        productAPI = retrofit.create(ProductAPI::class.java)
        productRepository = ProductRepository(productAPI)
    }

}