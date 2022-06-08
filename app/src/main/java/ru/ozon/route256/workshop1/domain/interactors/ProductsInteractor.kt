package ru.ozon.route256.workshop1.domain.interactors

import ru.ozon.route256.workshop1.presentation.viewObject.ProductInListVO

interface ProductsInteractor {
    fun getProducts(): List<ProductInListVO>

    fun getProductById(guid: String): ProductInListVO
}