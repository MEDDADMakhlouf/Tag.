package com.example.testmoh.data.datasource

import com.example.testmoh.data.models.Order

/**
 * Provides mock data for the application. In a real application, this would interact
 * with a local database, remote API, or other data sources.
 */
object LocalDataSource {

    /**
     * Generates a list of mock orders for the home screen.
     */
    fun getMockOrders(): List<Order> {
        return listOf(
            Order(
                id = "778",
                time = "14:45",
                customerName = "Alex Sedric",
                amountDue = 15.0,
                isPaid = false,
                status = "En cours...",
                items = listOf(
                    "Steakhouse Menu",
                    "  • votre viande : Tenders",
                    "  • vos sauces : Algerinne, Burger",
                    "  • vos supplement : Gouda",
                    "  • votre boisson : Oasis Tropical"
                ),
                totalPrice = 20.99,
                tax = 2.00 // Example tax
            ),
            Order(
                id = "779",
                time = "14:45",
                customerName = "Alex Sedric",
                amountDue = 0.0, // Assuming paid for this one
                isPaid = true,
                status = "Prêt",
                items = listOf(
                    "Chicken Royal Menu",
                    "  • votre viande : Tenders",
                    "  • vos sauces : Algerinne, Burger",
                    "  • vos supplement : Gouda",
                    "  • votre boisson : Oasis Tropical"
                ),
                totalPrice = 20.99,
                tax = 2.00
            ),
            Order(
                id = "780",
                time = "14:45",
                customerName = "Martin Bern",
                amountDue = 0.0,
                isPaid = false, // Not paid, but status is "En cours..."
                status = "En cours...",
                items = listOf(
                    "Burger King - SM Lucena",
                    "  • votre viande : Tenders",
                    "  • vos sauces : Algerinne, Burger",
                    "  • vos supplement : Gouda",
                    "  • votre boisson : Oasis Tropical"
                ),
                totalPrice = 20.99,
                tax = 2.00
            ),
            Order(
                id = "781",
                time = "14:45",
                customerName = "Alex Sedric",
                amountDue = 0.0,
                isPaid = false,
                status = "En cours...",
                items = listOf(
                    "Steakhouse Menu",
                    "  • votre viande : Tenders",
                    "  • vos sauces : Algerinne, Burger",
                    "  • vos supplement : Gouda",
                    "  • votre boisson : Oasis Tropical"
                ),
                totalPrice = 20.99,
                tax = 2.00
            ),
            Order(
                id = "782",
                time = "14:45",
                customerName = "Margo Amec",
                amountDue = 0.0,
                isPaid = false,
                status = "En cours...",
                items = listOf(
                    "Chicken Royal Menu",
                    "  • votre viande : Tenders",
                    "  • vos sauces : Algerinne, Burger",
                    "  • vos supplement : Gouda",
                    "  • votre boisson : Oasis Tropical"
                ),
                totalPrice = 20.99,
                tax = 2.00
            )
        )
    }

    /**
     * Retrieves a specific order by its ID.
     */
    fun getOrderById(orderId: String): Order? {
        return getMockOrders().find { it.id == orderId }
    }
}