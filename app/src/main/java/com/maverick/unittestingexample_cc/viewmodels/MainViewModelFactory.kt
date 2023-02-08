package com.maverick.unittestingexample_cc.viewmodels

import androidx.lifecycle.*
import com.maverick.unittestingexample_cc.models.Product
import com.maverick.unittestingexample_cc.repository.ProductRepository
import com.maverick.unittestingexample_cc.utils.NetworkResult
import kotlinx.coroutines.launch

class MainViewModelFactory(private val repository: ProductRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}