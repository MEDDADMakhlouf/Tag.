package com.example.testmoh.ui.theme.screens.products

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testmoh.data.models.Product
import com.example.testmoh.ui.theme.CardBackgroundLight
import com.example.testmoh.ui.theme.LightGrayBackground
import com.example.testmoh.ui.theme.PrimaryOrange
import com.example.testmoh.ui.theme.SuccessGreen
import com.example.testmoh.ui.theme.TestMohTheme
import com.example.testmoh.ui.theme.TextOnLight
import com.example.testmoh.ui.theme.TextSecondaryDark
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
    val categories = listOf("Menus", "Burgers", "Accompagnements", "Boissons", "Desserts", "Autres")
    var selectedCategory by remember { mutableStateOf("Menus") }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp

    Row(modifier = modifier.fillMaxSize().background(Color.Black)) { // Root background is still black for sidebar
        Sidebar(
            selectedRoute = "products_screen",
            onNavigate = { route ->
                when (route) {
                    "home_screen" -> onBackClick()
                    "settings_screen" -> onBackClick()
                    else -> {}
                }
            }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(CardBackgroundLight) // Changed main content background to white
        ) {
            AppTopBar(title = "Produits", onBackClick = onBackClick)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = Constants.CONTENT_HORIZONTAL_PADDING,
                        vertical = 16.dp
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(Constants.BUTTON_HEIGHT)
                            .background(LightGrayBackground, RoundedCornerShape(Constants.CARD_CORNER_RADIUS - 2.dp))
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = TextSecondaryDark,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = "Rechercher un produit", fontSize = 16.sp, color = TextSecondaryDark)
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = { },
                        modifier = Modifier.size(Constants.BUTTON_HEIGHT),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                        shape = RoundedCornerShape(Constants.CARD_CORNER_RADIUS - 2.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Product",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 0.dp)
                ) {
                    items(categories) { category ->
                        CategoryTab(
                            category = category,
                            isSelected = category == selectedCategory,
                            onCategorySelected = { selectedCategory = it }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = selectedCategory,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextOnLight, // Black text on white background
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            LazyVerticalGrid(
                columns = if (isLandscape) GridCells.Fixed(4) else GridCells.Fixed(2), // FIX: 2 columns in portrait, 4 in landscape
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = Constants.CONTENT_HORIZONTAL_PADDING, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(products.filter { it.category == selectedCategory || selectedCategory == "Menus" }) { product ->
                    ProductCard(product = product)
                }
            }
        }
    }
}

@Composable
fun CategoryTab(category: String, isSelected: Boolean, onCategorySelected: (String) -> Unit) {
    val backgroundColor = if (isSelected) PrimaryOrange else Color.Transparent
    val textColor = if (isSelected) Color.White else TextSecondaryDark

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable { onCategorySelected(category) }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = category,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun ProductCard(product: Product, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(CardBackgroundLight, RoundedCornerShape(Constants.CARD_CORNER_RADIUS))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .background(Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("IMG", color = TextSecondaryDark, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = product.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = TextOnLight,
            textAlign = TextAlign.Center
        )
        Text(
            text = product.description,
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${String.format("%.2f", product.price)} €",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextOnLight
        )

        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Toggle availability */ },
            modifier = Modifier
                .width(80.dp)
                .height(32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = if (product.isAvailable) SuccessGreen else Color.Red),
            shape = RoundedCornerShape(20.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            Text(if (product.isAvailable) "On" else "Off", color = Color.White, fontSize = 12.sp)
        }
    }
}

@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun PreviewProductsScreenLandscape() {
    TestMohTheme {
        Surface {
            ProductsScreen(onBackClick = {})
        }
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun PreviewProductsScreenPortrait() {
    TestMohTheme {
        Surface {
            ProductsScreen(onBackClick = {})
        }
    }
}
