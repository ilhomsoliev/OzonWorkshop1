package ru.ozon.route256.workshop1.data.dto

data class ProductInListDTO(
    val guid: String,
    val image: String,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean
)
