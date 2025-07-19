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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.testmoh.ui.theme.TestMohTheme
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
            .size(width = Constants.POS_SCREEN_WIDTH, height = Constants.POS_SCREEN_HEIGHT)
            .background(Color.Black)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            AppTopBar(
                title = "Détails de la commande",
                onBackClick = onBackClick
            )

            if (order == null) {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Chargement...", color = Color.Black, fontSize = 16.sp)
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
            .background(Color.White)
            .padding(Constants.CONTENT_START_PADDING),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item {
                Text(
                    text = "Commande #${order.id}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Client: ${order.customerName}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Heure: ${order.time}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Articles:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
            items(order.items) { item ->
                Text(text = item, fontSize = 14.sp, color = Color.DarkGray)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Total:", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(text = "${String.format("%.2f", order.totalPrice)} €", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "TVA:", fontSize = 14.sp, color = Color.Gray)
                    Text(text = "${String.format("%.2f", order.tax)} €", fontSize = 14.sp, color = Color.Gray)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "À payer:", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
                    Text(text = "${String.format("%.2f", order.amountDue)} €", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = Color.Red)
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F3460))
            ) {
                Text("Payer", color = Color.White, fontSize = 16.sp)
            }
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text("Imprimer", color = Color.Black, fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 230, heightDp = 315)
@Composable
fun PreviewOrderDetailsScreen() {
    TestMohTheme {
        Surface {
            OrderDetailsScreen(orderId = "778", onBackClick = {})
        }
    }
}
