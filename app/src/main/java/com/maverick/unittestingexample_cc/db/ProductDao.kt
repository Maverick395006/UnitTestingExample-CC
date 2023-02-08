package com.maverick.unittestingexample_cc.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productCache: ProductCache): Long

    @Query("SELECT * FROM products")
    suspend fun get(): List<ProductCache>

}