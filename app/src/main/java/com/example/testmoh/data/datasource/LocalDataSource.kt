package com.example.testmoh.data.datasource

import com.example.testmoh.data.models.Order
import com.example.testmoh.data.models.Product
import com.example.testmoh.data.models.SettingsOption

object LocalDataSource {

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
                tax = 2.00
            ),
            Order(
                id = "779",
                time = "14:45",
                customerName = "Alex Sedric",
                amountDue = 0.0,
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
                isPaid = false,
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

    fun getOrderById(orderId: String): Order? {
        return getMockOrders().find { it.id == orderId }
    }

    fun getMockProducts(): List<Product> {
        return listOf(
            Product("P001", "Burger Classique", "Burgers", 10.50),
            Product("P002", "Frites", "Accompagnements", 3.00),
            Product("P003", "Coca-Cola", "Boissons", 2.50),
            Product("P004", "Salade César", "Salades", 8.00),
            Product("P005", "Pizza Margherita", "Pizzas", 12.00),
            Product("P006", "Café", "Boissons", 1.80),
            Product("P007", "Cheeseburger", "Burgers", 11.00),
            Product("P008", "Milkshake Fraise", "Boissons", 4.50)
        )
    }

    fun getMockSettingsOptions(): List<SettingsOption> {
        return listOf(
            SettingsOption("S001", "Heures d'ouverture", "9:00 - 22:00"),
            SettingsOption("S002", "Gestion des employés", null),
            SettingsOption("S003", "Paramètres de paiement", "Terminal, Espèces"),
            SettingsOption("S004", "Notifications", "Activées"),
            SettingsOption("S005", "Imprimante", "Connectée"),
            SettingsOption("S006", "Support", null),
            SettingsOption("S007", "À propos", "Version 1.0.0")
        )
    }
}
