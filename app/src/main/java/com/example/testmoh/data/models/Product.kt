package com.example.testmoh.data.models

data class Product(
    val id: String,
    val name: String,
    val category: String,
    val price: Double,
    val description: String="",
    val isAvailable: Boolean = true
)
