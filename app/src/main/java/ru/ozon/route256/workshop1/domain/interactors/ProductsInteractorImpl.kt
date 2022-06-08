package ru.ozon.route256.workshop1.domain.interactors

import ru.ozon.route256.workshop1.domain.repositories.ProductsRepository
import ru.ozon.route256.workshop1.presentation.viewObject.ProductInListVO

class ProductsInteractorImpl(val repository: ProductsRepository) : ProductsInteractor {

    override fun getProducts(): List<ProductInListVO> = repository.getProducts()

    override fun getProductById(guid: String): ProductInListVO = repository.getProductById(guid)
}