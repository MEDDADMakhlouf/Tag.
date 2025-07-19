package com.example.testmoh.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmoh.data.datasource.LocalDataSource
import com.example.testmoh.data.models.Order
import com.example.testmoh.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: AppRepository = AppRepository(LocalDataSource)
) : ViewModel() {

    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders

    private val _showConnectionErrorDialog = MutableStateFlow(false)
    val showConnectionErrorDialog: StateFlow<Boolean> = _showConnectionErrorDialog

    init {
        loadOrders()
    }

    fun loadOrders() {
        viewModelScope.launch {
            _orders.value = repository.getAllOrders()
        }
    }

    fun toggleConnectionErrorDialog(show: Boolean) {
        _showConnectionErrorDialog.value = show
    }
}
