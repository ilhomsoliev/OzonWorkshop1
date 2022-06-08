package ru.ozon.route256.workshop1.sources

import ru.ozon.route256.workshop1.data.dto.ProductInListDTO
import ru.ozon.route256.workshop1.presentation.viewObject.ProductInListVO

fun ProductInListDTO.toVO() : ProductInListVO =
    ProductInListVO(guid, image, name, price, rating, isFavorite, isInCart)