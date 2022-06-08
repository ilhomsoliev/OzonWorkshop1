package ru.ozon.route256.workshop1.data.repositoryImpl

import ru.ozon.route256.workshop1.domain.repositories.ProductsRepository
import ru.ozon.route256.workshop1.presentation.viewObject.ProductInListVO
import ru.ozon.route256.workshop1.sources.toVO

class MockProductsRepositoryImpl : ProductsRepository {
    override fun getProducts(): List<ProductInListVO> = productInListDTOs.map { it.toVO() }

    override fun getProductById(guid: String): ProductInListVO =
        productInListDTOs.filter { it.guid == guid }[0].toVO()
}