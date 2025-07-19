package com.example.testmoh.ui.theme.screens.products


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
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
import com.example.testmoh.data.models.Product
import com.example.testmoh.ui.theme.TestMohTheme
import com.example.testmoh.ui.theme.common.AppTopBar
import com.example.testmoh.ui.theme.common.Sidebar
import com.example.testmoh.util.Constants
import com.example.testmoh.viewmodel.ProductsViewModel

@Composable
fun ProductsScreen(
    onBackClick: () -> Unit,
    productsViewModel: ProductsViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val products by productsViewModel.products.collectAsState()

    Box(
        modifier = modifier
            .size(width = Constants.POS_SCREEN_WIDTH, height = Constants.POS_SCREEN_HEIGHT)
            .background(Color.Black)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Sidebar(
                selectedRoute = "products_screen",
                onNavigate = { route ->
                    when (route) {
                        "home_screen" -> onBackClick()
                        "settings_screen" -> onBackClick()
                    }
                }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.White)
            ) {
                AppTopBar(title = "Produits", onBackClick = onBackClick)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Constants.CONTENT_START_PADDING, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(35.dp)
                            .background(Color.LightGray.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.Gray, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Rechercher un produit", fontSize = 12.sp, color = Color.Gray)
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { },
                        modifier = Modifier.size(35.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F3460)),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Product", tint = Color.White)
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = Constants.CONTENT_START_PADDING, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(products) { product ->
                        ProductCard(product = product)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = product.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = product.category, fontSize = 12.sp, color = Color.Gray)
        }
        Text(text = "${String.format("%.2f", product.price)} €", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
    }
}

@Preview(showBackground = true, widthDp = 230, heightDp = 315)
@Composable
fun PreviewProductsScreen() {
    TestMohTheme {
        Surface {
            ProductsScreen(onBackClick = {})
        }
    }
}
