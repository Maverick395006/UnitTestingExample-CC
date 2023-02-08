package com.maverick.unittestingexample_cc.api

import com.maverick.unittestingexample_cc.models.Product
import com.maverick.unittestingexample_cc.utils.EntityMapper

class RemoteMapper() : EntityMapper<ProductRemote, Product> {

    override fun mapFromEntity(entity: ProductRemote): Product {
        return Product(name = entity.title!!, image = entity.image!!)
    }

    override fun mapToEntity(domainModel: Product): ProductRemote {
        return ProductRemote(title = domainModel.name, image = domainModel.image)
    }

    fun mapFromEntityList(entities: List<ProductRemote>): List<Product> {
        return entities.map { mapFromEntity(it) }
    }

}