package com.example.testmoh.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmoh.data.datasource.LocalDataSource
import com.example.testmoh.data.models.Order
import com.example.testmoh.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the HomeScreen. Manages the state and business logic related to orders.
 */
class HomeViewModel(
    private val repository: AppRepository = AppRepository(LocalDataSource) // Default to LocalDataSource for simplicity
) : ViewModel() {

    // StateFlow to hold the list of orders, observable by the UI.
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders

    // StateFlow to manage the visibility of the connection error dialog.
    private val _showConnectionErrorDialog = MutableStateFlow(false)
    val showConnectionErrorDialog: StateFlow<Boolean> = _showConnectionErrorDialog

    init {
        // Load orders when the ViewModel is initialized
        loadOrders()
    }

    /**
     * Loads the list of orders from the repository.
     */
    fun loadOrders() {
        viewModelScope.launch {
            // In a real app, you'd handle loading states, errors, etc.
            _orders.value = repository.getAllOrders()
        }
    }

    /**
     * Toggles the visibility of the connection error dialog.
     * @param show True to show the dialog, false to hide it.
     */
    fun toggleConnectionErrorDialog(show: Boolean) {
        _showConnectionErrorDialog.value = show
    }

    // You can add more functions here for order-related actions (e.g., markOrderAsPaid, filterOrders)
}
