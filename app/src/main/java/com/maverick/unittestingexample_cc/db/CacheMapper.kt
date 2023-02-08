package com.maverick.unittestingexample_cc.db

import com.maverick.unittestingexample_cc.models.Product
import com.maverick.unittestingexample_cc.utils.EntityMapper

class CacheMapper() : EntityMapper<ProductCache, Product> {

    override fun mapFromEntity(entity: ProductCache): Product {
        return Product(name = entity.productName, image = entity.imageUrl)
    }

    override fun mapToEntity(domainModel: Product): ProductCache {
        return ProductCache(productName = domainModel.name, imageUrl = domainModel.image)
    }

    fun mapFromEntityList(entities: List<ProductCache>): List<Product> {
        return entities.map { mapFromEntity(it) }
    }

}