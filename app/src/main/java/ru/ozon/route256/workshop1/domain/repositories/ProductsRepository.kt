package ru.ozon.route256.workshop1.domain.repositories

import ru.ozon.route256.workshop1.presentation.viewObject.ProductInListVO

interface ProductsRepository{
    fun getProducts(): List<ProductInListVO>
    fun getProductById(guid: String): ProductInListVO

}