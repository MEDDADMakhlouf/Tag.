package com.example.testmoh.ui.theme.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues // Added for contentPadding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward // Correct Import
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testmoh.data.models.Order
import com.example.testmoh.navigation.AppRoutes // Correct Import
import com.example.testmoh.ui.common.AppTopBar // Correct Import
import com.example.testmoh.ui.screens.common.ConnectionErrorDialog // Correct Import
import com.example.testmoh.ui.common.Sidebar // Correct Import
import com.example.testmoh.ui.theme.TestMohTheme
import com.example.testmoh.util.Constants
import com.example.testmoh.viewmodel.HomeViewModel

/**
 * Composable function for the Home Screen (Nouvelle commande).
 * This screen displays a list of orders and includes the sidebar and top bar.
 *
 * @param onNavigateToOrderDetails Lambda to navigate to order details, accepts orderId.
 * @param onNavigateToProducts Lambda to navigate to products screen.
 * @param onNavigateToSettings Lambda to navigate to settings screen.
 * @param onShowErrorDialog Lambda to show the connection error dialog.
 * @param homeViewModel The ViewModel for this screen, provided by Hilt/ViewModel factory.
 */
@Composable
fun HomeScreen(
    onNavigateToOrderDetails: (String) -> Unit, // Changed to accept orderId
    onNavigateToProducts: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onShowErrorDialog: () -> Unit,
    homeViewModel: HomeViewModel = viewModel(), // Get ViewModel instance
    modifier: Modifier = Modifier
) {
    // Observe orders and dialog visibility from the ViewModel
    val orders by homeViewModel.orders.collectAsState()
    val showConnectionErrorDialog by homeViewModel.showConnectionErrorDialog.collectAsState()

    Box(
        modifier = modifier
            .size(width = Constants.POS_SCREEN_WIDTH, height = Constants.POS_SCREEN_HEIGHT)
            .background(Color.Black) // Main background color as per design
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Sidebar on the left
            Sidebar(
                selectedRoute = AppRoutes.HOME_SCREEN, // Highlight Home icon
                onNavigate = { route ->
                    when (route) {
                        AppRoutes.HOME_SCREEN -> { /* Already on Home, do nothing or refresh */ }
                        AppRoutes.PRODUCTS_SCREEN -> onNavigateToProducts()
                        AppRoutes.SETTINGS_SCREEN -> onNavigateToSettings()
                    }
                }
            )

            // Main content area
            Column(
                modifier = Modifier
                    .weight(1f) // Takes remaining width
                    .fillMaxHeight()
                    .background(Color.White) // White background for the main content area
            ) {
                // Top Bar
                AppTopBar(title = "Nouvelle commande")

                // Search bar and filter (Placeholder for now)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Constants.CONTENT_START_PADDING, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Recherche Produit (Placeholder)", fontSize = 12.sp, color = Color.Gray)
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Filter", tint = Color.Gray, modifier = Modifier.size(18.dp))
                }

                // Main content area for orders (LazyColumn for scrollable list)
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = Constants.CONTENT_START_PADDING, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp) // Space between order cards
                ) {
                    // Display "Nouvelle commande" section
                    item {
                        Text(
                            text = "Nouvelle commande",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }

                    // Filter and display "Nouvelle commande" (paid = false)
                    items(orders.filter { !it.isPaid }) { order ->
                        OrderCard(order = order, onNavigateToOrderDetails = onNavigateToOrderDetails)
                    }

                    // Display "En cours..." section
                    item {
                        Text(
                            text = "En cours ...",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                        )
                    }

                    // Filter and display "En cours..." (status = "En cours...")
                    items(orders.filter { it.status == "En cours..." }) { order ->
                        OrderCard(order = order, onNavigateToOrderDetails = onNavigateToOrderDetails)
                    }

                    // Example of how to trigger the error dialog for testing
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { homeViewModel.toggleConnectionErrorDialog(true) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                        ) {
                            Text("Show Connection Error (Test)", color = Color.White)
                        }
                    }
                }
            }
        }

        // Display the connection error dialog if the state indicates it should be shown
        if (showConnectionErrorDialog) {
            ConnectionErrorDialog(
                onDismiss = { homeViewModel.toggleConnectionErrorDialog(false) }
            )
        }
    }
}

/**
 * Composable for a single order card displayed on the Home Screen.
 */
@Composable
fun OrderCard(
    order: Order,
    onNavigateToOrderDetails: (String) -> Unit
) {
    val cardBackgroundColor = if (order.isPaid) Color(0xFFFF9800) else Color(0xFF2196F3) // Orange for paid, blue for not paid
    val buttonText = if (order.isPaid) "Payer" else "Prêt" // Button text based on payment status

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(cardBackgroundColor, RoundedCornerShape(8.dp))
            .clickable { onNavigateToOrderDetails(order.id) } // Navigate to details on card click
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = order.time,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = order.customerName,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
            if (!order.isPaid) {
                Text(
                    text = "Reste à payer : ${order.amountDue} €",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            if (order.isPaid) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Payé",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Button(
                    onClick = { /* TODO: Handle Pay/Prêt click */ },
                    modifier = Modifier.width(60.dp).height(30.dp), // Button size
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(buttonText, color = Color.Black, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Détails",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 230, heightDp = 315)
@Composable
fun PreviewHomeScreen() {
    TestMohTheme {
        Surface {
            HomeScreen(
                onNavigateToOrderDetails = {},
                onNavigateToProducts = {},
                onNavigateToSettings = {},
                onShowErrorDialog = {}
            )
        }
    }
}
