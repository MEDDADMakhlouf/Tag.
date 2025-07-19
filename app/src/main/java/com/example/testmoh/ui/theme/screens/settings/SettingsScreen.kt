package com.example.testmoh.ui.theme.screens.settings


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
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
import com.example.testmoh.data.models.SettingsOption
import com.example.testmoh.ui.theme.TestMohTheme
import com.example.testmoh.ui.theme.common.AppTopBar
import com.example.testmoh.ui.theme.common.Sidebar
import com.example.testmoh.util.Constants
import com.example.testmoh.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    settingsViewModel: SettingsViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val settingsOptions by settingsViewModel.settingsOptions.collectAsState()

    Box(
        modifier = modifier
            .size(width = Constants.POS_SCREEN_WIDTH, height = Constants.POS_SCREEN_HEIGHT)
            .background(Color.Black)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Sidebar(
                selectedRoute = "settings_screen",
                onNavigate = { route ->
                    when (route) {
                        "home_screen" -> onBackClick()
                        "products_screen" -> onBackClick()
                    }
                }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.White)
            ) {
                AppTopBar(title = "Paramètres", onBackClick = onBackClick)

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = Constants.CONTENT_START_PADDING, vertical = 8.dp)
                ) {
                    items(settingsOptions) { option ->
                        SettingsItem(option = option)
                        Divider(color = Color.LightGray.copy(alpha = 0.3f), thickness = 1.dp)
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsItem(option: SettingsOption, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = option.title, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            if (option.subtitle != null) {
                Text(text = option.subtitle, fontSize = 12.sp, color = Color.Gray)
            }
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Détails",
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true, widthDp = 230, heightDp = 315)
@Composable
fun PreviewSettingsScreen() {
    TestMohTheme {
        Surface {
            SettingsScreen(onBackClick = {})
        }
    }
}
