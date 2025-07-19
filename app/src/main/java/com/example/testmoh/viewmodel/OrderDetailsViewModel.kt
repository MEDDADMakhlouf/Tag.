package com.example.testmoh.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmoh.data.datasource.LocalDataSource
import com.example.testmoh.data.models.Order
import com.example.testmoh.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderDetailsViewModel(
    private val repository: AppRepository = AppRepository(LocalDataSource)
) : ViewModel() {

    private val _order = MutableStateFlow<Order?>(null)
    val order: StateFlow<Order?> = _order

    fun loadOrderDetails(orderId: String) {
        viewModelScope.launch {
            _order.value = repository.getOrderById(orderId)
        }
    }
}
