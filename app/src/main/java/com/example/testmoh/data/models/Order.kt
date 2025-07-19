package com.example.testmoh.data.models

data class Order(
    val id: String,
    val time: String,
    val customerName: String,
    val amountDue: Double,
    val isPaid: Boolean,
    val status: String,
    val items: List<String> = emptyList(), // For order details screen
    val totalPrice: Double = 0.0,
    val tax: Double = 0.0
)
