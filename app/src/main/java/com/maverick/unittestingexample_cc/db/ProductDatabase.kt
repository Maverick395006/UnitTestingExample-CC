package com.maverick.unittestingexample_cc.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductCache::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getDatabase(context: Context): ProductDatabase {
            if (INSTANCE == null) {
                synchronized(ProductDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        ProductDatabase::class.java, "productDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}