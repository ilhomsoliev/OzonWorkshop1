package ru.ozon.route256.workshop1.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ozon.route256.workshop1.domain.interactors.ProductsInteractor
import ru.ozon.route256.workshop1.presentation.viewObject.ProductInListVO

class ProductsViewModel(val productsInteractor: ProductsInteractor) : ViewModel() {

    private val _productLD = MutableLiveData<List<ProductInListVO>>()
    val productLD:LiveData<List<ProductInListVO>> = _productLD

    init {
        _productLD.postValue(productsInteractor.getProducts())
    }

    fun getProductById(guid:String) = productsInteractor.getProductById(guid)
}