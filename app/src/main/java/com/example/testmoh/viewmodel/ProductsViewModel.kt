package com.example.testmoh.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmoh.data.datasource.LocalDataSource
import com.example.testmoh.data.models.Product
import com.example.testmoh.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val repository: AppRepository = AppRepository(LocalDataSource)
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _showConnectionErrorDialog = MutableStateFlow(false)
    val showConnectionErrorDialog: StateFlow<Boolean> = _showConnectionErrorDialog

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            // Removed imageUrl from Product constructor calls in dummy data
            _products.value = LocalDataSource.getMockProducts()
        }
    }

    fun toggleProductAvailability(productId: String, isAvailable: Boolean) {
        viewModelScope.launch {
            _products.value = _products.value.map { product ->
                if (product.id == productId) {
                    product.copy(isAvailable = isAvailable)
                } else {
                    product
                }
            }
        }
    }

    fun toggleConnectionErrorDialog(show: Boolean) {
        _showConnectionErrorDialog.value = show
    }
}
