package com.maverick.unittestingexample_cc.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverick.unittestingexample_cc.models.ProductListItem
import com.maverick.unittestingexample_cc.repository.ProductRepository
import com.maverick.unittestingexample_cc.utils.NetworkResult
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _product = MutableLiveData<NetworkResult<List<ProductListItem>>>()
    val products: LiveData<NetworkResult<List<ProductListItem>>>
        get() = _product

    fun getProducts() {
        viewModelScope.launch {
            val result = repository.getProducts()
            _product.postValue(result)
        }
    }

}