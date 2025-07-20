package com.example.testmoh.ui.theme.screens.orderdetails


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testmoh.data.models.Order
import com.example.testmoh.ui.theme.BackgroundDark
import com.example.testmoh.ui.theme.LightGrayBackground
import com.example.testmoh.ui.theme.PrimaryOrange
import com.example.testmoh.ui.theme.TestMohTheme
import com.example.testmoh.ui.theme.TextPrimaryDark
import com.example.testmoh.ui.theme.TextSecondaryDark
import com.example.testmoh.ui.theme.common.AppTopBar
import com.example.testmoh.util.Constants
import com.example.testmoh.viewmodel.OrderDetailsViewModel

@Composable
fun OrderDetailsScreen(
    orderId: String?,
    onBackClick: () -> Unit,
    orderDetailsViewModel: OrderDetailsViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val order by orderDetailsViewModel.order.collectAsState()

    orderId?.let {
        orderDetailsViewModel.loadOrderDetails(it)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            AppTopBar(
                title = "Commande #778",
                onBackClick = onBackClick
            )

            if (order == null) {
                Box(
                    modifier = Modifier.fillMaxSize().background(BackgroundDark),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Chargement...", color = TextPrimaryDark, fontSize = 20.sp)
                }
            } else {
                OrderDetailsContent(order = order!!)
            }
        }
    }
}

@Composable
fun OrderDetailsContent(order: Order, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(horizontal = Constants.CONTENT_HORIZONTAL_PADDING),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f), // Ensures LazyColumn takes all available space above buttons
            contentPadding = PaddingValues(vertical = Constants.SCREEN_VERTICAL_PADDING),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Commande #${order.id}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimaryDark
                )
                Text(
                    text = "Client: ${order.customerName}",
                    fontSize = 18.sp,
                    color = TextSecondaryDark
                )
                Text(
                    text = "Heure: ${order.time}",
                    fontSize = 18.sp,
                    color = TextSecondaryDark
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Articles:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimaryDark
                )
            }
            items(order.items) { item ->
                Text(text = "• $item", fontSize = 18.sp, color = TextSecondaryDark)
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Prix Total", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = TextPrimaryDark)
                    Text(text = "${String.format("%.2f", order.totalPrice)} $", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = TextPrimaryDark)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "TTC", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = TextPrimaryDark)
                    Text(text = "${String.format("%.2f", order.totalPrice - order.tax)} $", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = TextPrimaryDark)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "TVA", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = TextPrimaryDark)
                    Text(text = "${String.format("%.2f", order.tax)} $", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = TextPrimaryDark)
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "À payer:", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimaryDark)
                    Text(text = "${String.format("%.2f", order.amountDue)} €", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = PrimaryOrange)
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Constants.SCREEN_VERTICAL_PADDING),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Constants.BUTTON_HEIGHT),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                shape = RoundedCornerShape(Constants.CARD_CORNER_RADIUS)
            ) {
                Text("Payer", color = TextPrimaryDark, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            }
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Constants.BUTTON_HEIGHT),
                colors = ButtonDefaults.buttonColors(containerColor = LightGrayBackground.copy(alpha = 0.2f)),
                shape = RoundedCornerShape(Constants.CARD_CORNER_RADIUS)
            ) {
                Text("Imprimer", color = TextPrimaryDark, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun PreviewOrderDetailsScreenLandscape() {
    TestMohTheme {
        Surface {
            OrderDetailsScreen(orderId = "778", onBackClick = {})
        }
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun PreviewOrderDetailsScreenPortrait() {
    TestMohTheme {
        Surface {
            OrderDetailsScreen(orderId = "778", onBackClick = {})
        }
    }
}
