package com.example.testmoh.data.repository

import com.example.testmoh.data.datasource.LocalDataSource
import com.example.testmoh.data.models.Order

class AppRepository(private val localDataSource: LocalDataSource) {

    fun getAllOrders(): List<Order> {
        return localDataSource.getMockOrders()
    }

    fun getOrderById(orderId: String): Order? {
        return localDataSource.getOrderById(orderId)
    }
}
