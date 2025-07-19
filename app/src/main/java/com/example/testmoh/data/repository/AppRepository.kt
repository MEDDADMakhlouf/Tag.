package com.example.testmoh.data.repository

import com.example.testmoh.data.datasource.LocalDataSource
import com.example.testmoh.data.models.Order

/**
 * Repository class that abstracts the data layer from the rest of the application.
 * It decides whether to fetch data from a local source, remote API, etc.
 * For this test, it primarily uses LocalDataSource.
 */
class AppRepository(private val localDataSource: LocalDataSource) {

    /**
     * Retrieves a list of all orders.
     */
    fun getAllOrders(): List<Order> {
        return localDataSource.getMockOrders()
    }

    /**
     * Retrieves a specific order by its ID.
     * @param orderId The ID of the order to retrieve.
     * @return The Order object if found, null otherwise.
     */
    fun getOrderById(orderId: String): Order? {
        return localDataSource.getOrderById(orderId)
    }

    // You can add more methods here for other data operations (e.g., addOrder, updateOrder, getProducts, etc.)
}
