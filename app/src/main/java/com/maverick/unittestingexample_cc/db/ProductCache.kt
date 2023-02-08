package com.maverick.unittestingexample_cc.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "products", indices = [Index(
        value = ["productName"],
        unique = true
    )]
)
data class ProductCache(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "productName")
    var productName: String,

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String,

    )
