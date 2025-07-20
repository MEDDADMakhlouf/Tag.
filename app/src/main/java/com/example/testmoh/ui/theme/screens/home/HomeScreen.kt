package com.example.testmoh.ui.theme.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.List
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
import com.example.testmoh.navigation.AppRoutes
import com.example.testmoh.ui.theme.BackgroundDark
import com.example.testmoh.ui.theme.CardBackgroundLight
import com.example.testmoh.ui.theme.PrimaryBlue
import com.example.testmoh.ui.theme.PrimaryOrange
import com.example.testmoh.ui.theme.TestMohTheme
import com.example.testmoh.ui.theme.TextOnLight
import com.example.testmoh.ui.theme.TextPrimaryDark
import com.example.testmoh.ui.theme.TextSecondaryDark
import com.example.testmoh.ui.theme.common.AppTopBar
import com.example.testmoh.ui.theme.common.Sidebar
import com.example.testmoh.ui.theme.screens.common.ConnectionErrorDialog
import com.example.testmoh.util.Constants
import com.example.testmoh.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onNavigateToOrderDetails: (String) -> Unit,
    onNavigateToProducts: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onShowErrorDialog: () -> Unit, // Renamed for clarity, though not directly used here for initial call
    homeViewModel: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val orders by homeViewModel.orders.collectAsState()
    val showConnectionErrorDialog by homeViewModel.showConnectionErrorDialog.collectAsState()

    Row(modifier = modifier.fillMaxSize().background(Color.Black)) { // Root background is black
        Sidebar(
            selectedRoute = AppRoutes.HOME_SCREEN,
            onNavigate = { route ->
                when (route) {
                    AppRoutes.HOME_SCREEN -> { /* Already on home screen */ }
                    AppRoutes.PRODUCTS_SCREEN -> onNavigateToProducts()
                    AppRoutes.SETTINGS_SCREEN -> onNavigateToSettings()
                }
            }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(BackgroundDark) // Main content background
        ) {
            AppTopBar(title = "Nouvelle commande", isConnected = true) // Assumed connected for this screen

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = Constants.CONTENT_HORIZONTAL_PADDING,
                        vertical = 16.dp // Adjusted vertical padding
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Recherche Produit", // Placeholder text
                    fontSize = 16.sp,
                    color = TextSecondaryDark // Grey text
                )
                // Using List icon as a placeholder for filter as seen in design
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Filter",
                    tint = TextSecondaryDark,
                    modifier = Modifier.size(24.dp)
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = Constants.CONTENT_HORIZONTAL_PADDING, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp) // Adjusted space between cards
            ) {
                item {
                    Text(
                        text = "Nouvelle commande",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimaryDark, // White text
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                items(orders.filter { !it.isPaid }) { order ->
                    OrderCard(order = order, onNavigateToOrderDetails = onNavigateToOrderDetails)
                }

                item {
                    Text(
                        text = "En cours ...",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimaryDark, // White text
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                }

                items(orders.filter { it.status == "En cours..." }) { order ->
                    OrderCard(order = order, onNavigateToOrderDetails = onNavigateToOrderDetails)
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    // This is the test button, let's style it according to the app's button style
                    Button(
                        onClick = { homeViewModel.toggleConnectionErrorDialog(true) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Constants.BUTTON_HEIGHT),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange), // Use an app theme color
                        shape = RoundedCornerShape(Constants.CARD_CORNER_RADIUS)
                    ) {
                        Text("Show Connection Error (Test)", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
    }

    if (showConnectionErrorDialog) {
        ConnectionErrorDialog(
            onDismiss = { homeViewModel.toggleConnectionErrorDialog(false) }
        )
    }
}

@Composable
fun OrderCard(
    order: Order,
    onNavigateToOrderDetails: (String) -> Unit
) {
    val cardBackgroundColor = if (order.isPaid) PrimaryOrange else CardBackgroundLight // Orange for paid, white for unpaid
    val buttonBackgroundColor = if (order.isPaid) CardBackgroundLight else PrimaryBlue // White for "Payer", Blue for "Prêt"
    val buttonTextColor = if (order.isPaid) TextOnLight else Color.White // Black for "Payer", White for "Prêt"
    val buttonText = if (order.isPaid) "Payer" else "Prêt"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(cardBackgroundColor, RoundedCornerShape(Constants.CARD_CORNER_RADIUS))
            .clickable { onNavigateToOrderDetails(order.id) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = order.time,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (order.isPaid) Color.White else TextOnLight // White text on orange, black on white
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = order.customerName,
                    fontSize = 18.sp,
                    color = if (order.isPaid) Color.White else TextOnLight
                )
            }
            if (!order.isPaid) {
                Text(
                    text = "Reste à payer : ${order.amountDue} €",
                    fontSize = 16.sp,
                    color = Color.Red.copy(alpha = 0.8f) // Red for amount due as per design
                )
            } else {
                Text(
                    text = "Payé", // Text for paid status
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .width(100.dp) // Adjusted width for specific buttons on card
                    .height(40.dp), // Adjusted height
                colors = ButtonDefaults.buttonColors(containerColor = buttonBackgroundColor),
                shape = RoundedCornerShape(Constants.CARD_CORNER_RADIUS - 2.dp) // Slightly smaller radius than card
            ) {
                Text(buttonText, color = buttonTextColor, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Détails",
                tint = if (order.isPaid) Color.White else TextOnLight, // White on orange, black on white
                modifier = Modifier.size(28.dp) // Adjusted icon size
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
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
