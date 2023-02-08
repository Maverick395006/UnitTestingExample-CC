package com.maverick.unittestingexample_cc

import android.app.Application
import com.maverick.unittestingexample_cc.api.ProductAPI
import com.maverick.unittestingexample_cc.api.RemoteMapper
import com.maverick.unittestingexample_cc.db.CacheMapper
import com.maverick.unittestingexample_cc.db.ProductDao
import com.maverick.unittestingexample_cc.db.ProductDatabase
import com.maverick.unittestingexample_cc.repository.ProductRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoreApplication : Application() {

    lateinit var productRepository: ProductRepository
    lateinit var productAPI: ProductAPI
    lateinit var productDao: ProductDao
    lateinit var remoteMapper: RemoteMapper
    lateinit var cacheMapper: CacheMapper

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fakestoreapi.com/")
            .build()

        productAPI = retrofit.create(ProductAPI::class.java)
        productDao = ProductDatabase.getDatabase(this).productDao()
        remoteMapper = RemoteMapper()
        cacheMapper = CacheMapper()

        productRepository = ProductRepository(productAPI,productDao,remoteMapper,cacheMapper)
    }

}