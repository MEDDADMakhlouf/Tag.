package com.example.testmoh.ui.theme.screens.products

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import com.example.testmoh.navigation.AppRoutes
import com.example.testmoh.ui.theme.BackgroundWithe
import com.example.testmoh.ui.theme.ButtonBorderColor
import com.example.testmoh.ui.theme.CardBackgroundLight
import com.example.testmoh.ui.theme.LightGrayBackground
import com.example.testmoh.ui.theme.PrimaryOrange
import com.example.testmoh.ui.theme.TestMohTheme
import com.example.testmoh.ui.theme.TextOnLight
import com.example.testmoh.ui.theme.TextPrimaryDark
import com.example.testmoh.ui.theme.TextSecondaryDark
import com.example.testmoh.ui.theme.common.AppTopBar
import com.example.testmoh.ui.theme.common.Sidebar
import com.example.testmoh.ui.theme.screens.common.ConnectionErrorDialog
import com.example.testmoh.util.Constants
import com.example.testmoh.viewmodel.ProductsViewModel

@Composable
fun ProductsScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onShowErrorDialog: () -> Unit,
    productsViewModel: ProductsViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val products by productsViewModel.products.collectAsState()
    val showConnectionErrorDialog by productsViewModel.showConnectionErrorDialog.collectAsState()

    val categories = listOf("Menus", "Boissons", "Accompagnements", "Desserts", "Autres")
    var selectedCategory by remember { mutableStateOf("Menus") }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp

    val lazyGridState = rememberLazyGridState()
    val isTopBarVisible by remember {
        derivedStateOf {
            lazyGridState.firstVisibleItemIndex == 0 && lazyGridState.firstVisibleItemScrollOffset == 0
        }
    }

    Row(modifier = modifier.fillMaxSize().background(Color.Black)) {
        Sidebar(
            selectedRoute = AppRoutes.PRODUCTS_SCREEN,
            onNavigate = { route ->
                when (route) {
                    AppRoutes.HOME_SCREEN -> onNavigateToHome()
                    AppRoutes.PRODUCTS_SCREEN -> {}
                    AppRoutes.SETTINGS_SCREEN -> onNavigateToSettings()
                }
            }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(BackgroundWithe)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    columns = if (isLandscape) GridCells.Fixed(4) else GridCells.Fixed(2),
                    state = lazyGridState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        start = Constants.CONTENT_HORIZONTAL_PADDING,
                        end = Constants.CONTENT_HORIZONTAL_PADDING,
                        top = if (isTopBarVisible) Constants.TOP_BAR_HEIGHT + 16.dp else 8.dp,
                        bottom = 8.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        ProductSearchAndFilterSection()
                    }

                    item(span = { GridItemSpan(maxLineSpan) }) {
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
                    }

                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text(
                            text = selectedCategory,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextOnLight,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                    }

                    items(products.filter { it.category == selectedCategory || selectedCategory == "Menus" }) { product ->
                        ProductCard(
                            product = product,
                            onToggleAvailability = { productId, isAvailable ->
                                productsViewModel.toggleProductAvailability(productId, isAvailable)
                            }
                        )
                    }

                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = { productsViewModel.toggleConnectionErrorDialog(true) },
                            modifier = Modifier.fillMaxWidth().height(Constants.BUTTON_HEIGHT),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                            shape = RoundedCornerShape(Constants.CARD_CORNER_RADIUS)
                        ) {
                            Text("Show Connection Error (Test)", color = Color.White, fontSize = 16.sp)
                        }
                    }
                }

                Column(modifier = Modifier.align(Alignment.TopCenter).fillMaxWidth()) {
                    AnimatedVisibility(
                        visible = isTopBarVisible,
                        enter = slideInVertically(initialOffsetY = { -it }, animationSpec = tween(durationMillis = 300)),
                        exit = slideOutVertically(targetOffsetY = { -it }, animationSpec = tween(durationMillis = 300)),
                    ) {
                        AppTopBar(title = "Produits", onBackClick = null, isConnected = true)
                    }
                }
            }
        }
    }

    if (showConnectionErrorDialog) {
        ConnectionErrorDialog(
            onDismiss = { productsViewModel.toggleConnectionErrorDialog(false) }
        )
    }
}

@Composable
fun ProductSearchAndFilterSection(modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("Rechercher un produit", color = TextSecondaryDark) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = TextSecondaryDark
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightGrayBackground,
                unfocusedContainerColor = LightGrayBackground,
                disabledContainerColor = LightGrayBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(Constants.CARD_CORNER_RADIUS),
            modifier = Modifier
                .weight(1f)
                .height(Constants.BUTTON_HEIGHT)
        )
    }
}

@Composable
fun CategoryTab(category: String, isSelected: Boolean, onCategorySelected: (String) -> Unit) {
    val backgroundColor = if (isSelected) LightGrayBackground else Color.White
    val textColor = if (isSelected) TextOnLight else TextOnLight

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .border(1.dp, ButtonBorderColor, RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onCategorySelected(category) }
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
fun ProductCard(
    product: Product,
    onToggleAvailability: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val cardWidth = 200.dp
    val cardHeight = 220.dp

    Surface(
        modifier = modifier
            .size(width = cardWidth, height = cardHeight)
            .clip(RoundedCornerShape(Constants.CARD_CORNER_RADIUS)),
        color = CardBackgroundLight,
        shadowElevation = 4.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(Constants.CARD_CORNER_RADIUS - 4.dp))
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "IMG",
                        color = TextSecondaryDark,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = product.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimaryDark,
                        maxLines = 1
                    )
                    Text(
                        text = product.description,
                        fontSize = 12.sp,
                        color = TextSecondaryDark,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${product.price} €",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryOrange
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun PreviewProductsScreenLandscape() {
    TestMohTheme {
        Surface {
            ProductsScreen(
                onNavigateToHome = {},
                onNavigateToSettings = {},
                onShowErrorDialog = {}
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun PreviewProductsScreenPortrait() {
    TestMohTheme {
        Surface {
            ProductsScreen(
                onNavigateToHome = {},
                onNavigateToSettings = {},
                onShowErrorDialog = {}
            )
        }
    }
}
