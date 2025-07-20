package com.example.testmoh.ui.theme.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmoh.ui.theme.TestMohTheme
import com.example.testmoh.ui.theme.SidebarBackground
import com.example.testmoh.ui.theme.TextSecondaryDark
import com.example.testmoh.ui.theme.TagTextGrey
import com.example.testmoh.util.Constants

data class SidebarItem(
    val icon: ImageVector,
    val contentDescription: String,
    val route: String
)

@Composable
fun Sidebar(
    selectedRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val iconSize = 28.dp

    val items = listOf(
        SidebarItem(Icons.Default.Home, "Accueil", "home_screen"),
        SidebarItem(Icons.Default.List, "Produits", "products_screen"),
        SidebarItem(Icons.Default.Settings, "Paramètres", "settings_screen")
    )

    Column(
        modifier = modifier
            .width(Constants.SIDEBAR_WIDTH)
            .fillMaxHeight()
            .background(SidebarBackground)
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "tag.",
            color = TagTextGrey,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items.forEach { item ->
                val tintColor = if (selectedRoute == item.route) Color.White else TextSecondaryDark
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription,
                    tint = tintColor,
                    modifier = Modifier
                        .size(iconSize)
                        .clickable { onNavigate(item.route) }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentDescription = "Déconnexion",
            tint = TextSecondaryDark,
            modifier = Modifier
                .size(iconSize)
                .clickable { }
                .padding(bottom = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSidebar() {
    TestMohTheme {
        Sidebar(selectedRoute = "home_screen", onNavigate = {})
    }
}
