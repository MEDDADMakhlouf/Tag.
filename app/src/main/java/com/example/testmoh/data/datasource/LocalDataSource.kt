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
        // Removed imageUrl from Product constructor calls
        return listOf(
            Product("P001", "Burger King - SM Lucena", "Menus", 7.50, "🔥 Grillé à la flamme pour un goût inimitable", true),
            Product("P002", "Chicken Royal Menu", "Menus", 8.00, "Un classique, poulet croustillant.", true),
            Product("P003", "Steakhouse Menu", "Menus", 9.00, "Le goût authentique du grill.", false), // Example unavailable
            Product("P004", "Coca-Cola 250CL", "Boissons", 2.50, "Boisson rafraîchissante.", true),
            Product("P005", "Sprite 250CL", "Boissons", 2.50, "Boisson pétillante au citron vert.", true),
            Product("P006", "Fanta Orange 250CL", "Boissons", 2.50, "Boisson fruitée à l'orange.", false), // Example unavailable
            Product("P007", "Frites Moyennes", "Accompagnements", 3.00, "Des frites croustillantes et dorées.", true),
            Product("P008", "Salade Mixte", "Accompagnements", 4.00, "Salade fraîche et variée.", true),
            Product("P009", "Tiramisu", "Desserts", 5.50, "Dessert italien classique.", true),
            Product("P010", "Brownie", "Desserts", 4.50, "Un brownie moelleux au chocolat.", true)
        )
    }

    fun getMockSettingsOptions(): List<SettingsOption> {
        return listOf(
            SettingsOption("S001", "Modification Horaires", "Gérez les heures d'ouverture"),
            SettingsOption("S005", "Imprimante", "Configuration de l'imprimante"),
            SettingsOption("S006", "Contacter l'assistance", "Obtenez de l'aide rapidement")
        )
    }
}
