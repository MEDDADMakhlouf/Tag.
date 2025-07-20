package com.example.testmoh.ui.theme.screens.settings


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testmoh.R
import com.example.testmoh.data.models.SettingsOption
import com.example.testmoh.ui.theme.CardBackgroundLight
import com.example.testmoh.ui.theme.LightGrayBackground
import com.example.testmoh.ui.theme.TestMohTheme
import com.example.testmoh.ui.theme.TextOnLight
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

    Row(modifier = modifier.fillMaxSize().background(Color.Black)) { // Root background is still black for sidebar
        Sidebar(
            selectedRoute = "settings_screen",
            onNavigate = { route ->
                when (route) {
                    "home_screen" -> onBackClick()
                    "products_screen" -> onBackClick()
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
            AppTopBar(title = "Paramètres", onBackClick = onBackClick)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(CardBackgroundLight),
                contentPadding = PaddingValues(horizontal = Constants.CONTENT_HORIZONTAL_PADDING, vertical = Constants.SCREEN_VERTICAL_PADDING)
            ) {
                item {
                    SettingsSection(
                        title = "Modification Horaires",
                        isExpandedByDefault = true
                    ) {
                        WorkingHoursSection()
                    }
                }
                item {
                    SettingsSection(title = "Imprimante") {
                        PrinterSection()
                    }
                }
                item {
                    SettingsSection(title = "Contacter l'assistance") {
                        ContactSupportSection()
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                items(settingsOptions.filter { it.title != "Modification Horaires" && it.title != "Imprimante" && it.title != "Contacter l'assistance" }) { option ->
                    SettingsItem(option = option)
                    Divider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 1.dp)
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
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = option.title, fontSize = 18.sp, fontWeight = FontWeight.Medium, color = TextOnLight)
            if (option.subtitle != null) {
                Text(text = option.subtitle, fontSize = 14.sp, color = Color.Gray)
            }
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Détails",
            tint = Color.Gray,
            modifier = Modifier.size(28.dp)
        )
    }
}

@Composable
fun SettingsSection(
    title: String,
    modifier: Modifier = Modifier,
    isExpandedByDefault: Boolean = false,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(isExpandedByDefault) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextOnLight
            )
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (expanded) "Collapse" else "Expand",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(expandFrom = Alignment.Top),
            exit = shrinkVertically(shrinkTowards = Alignment.Top)
        ) {
            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                content()
            }
        }
        Divider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 1.dp)
    }
}

@Composable
fun WorkingHoursSection() {
    val days = listOf("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche")
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        days.forEachIndexed { index, day ->
            val isSunday = day == "Dimanche"
            var isChecked by remember { mutableStateOf(!isSunday) }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = day,
                    fontSize = 16.sp,
                    color = TextOnLight,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.width(90.dp)
                )

                TimeInputBox(time = "08h30", isEnabled = isChecked)
                Spacer(modifier = Modifier.width(8.dp))
                TimeInputBox(time = "22h30", isEnabled = isChecked)

                Spacer(modifier = Modifier.width(16.dp))

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(LightGrayBackground, RoundedCornerShape(8.dp))
                        .clickable(enabled = isChecked) { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Time Slot",
                        tint = if (isChecked) Color.Black else Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(LightGrayBackground, RoundedCornerShape(8.dp))
                        .border(1.dp, if (isChecked) Color.Transparent else Color.Gray, RoundedCornerShape(8.dp))
                        .clickable { isChecked = !isChecked },
                    contentAlignment = Alignment.Center
                ) {
                    if (isChecked) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Checked",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TimeInputBox(time: String, isEnabled: Boolean) {
    val boxColor = if (isEnabled) LightGrayBackground else Color.LightGray.copy(alpha = 0.5f)
    val textColor = if (isEnabled) TextOnLight else Color.Gray
    val iconTint = if (isEnabled) Color.Black else Color.Gray

    Row(
        modifier = Modifier
            .width(90.dp)
            .height(36.dp)
            .background(boxColor, RoundedCornerShape(8.dp))
            .clickable(enabled = isEnabled) { }
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = time, fontSize = 16.sp, color = textColor)
        Icon(
            painter = painterResource(id = R.drawable.clock_icon
            ),
            contentDescription = "Time",
            tint = iconTint,
            modifier = Modifier.size(20.dp)
        )
    }
}


@Composable
fun PrinterSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        PrinterDropdown("Printer V54770")
        PrinterDropdown("Printer V54770")
    }
}

@Composable
fun PrinterDropdown(printerName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(LightGrayBackground, RoundedCornerShape(8.dp))
            .clickable { }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = printerName, fontSize = 16.sp, color = TextOnLight)
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "Select Printer",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ContactSupportSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Email support : exemple@support.com", fontSize = 16.sp, color = TextOnLight)
        Text(text = "Numéro de téléphone : 07 58 23 05 24", fontSize = 16.sp, color = TextOnLight)
    }
}


@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun PreviewSettingsScreenLandscape() {
    TestMohTheme {
        Surface {
            SettingsScreen(onBackClick = {})
        }
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun PreviewSettingsScreenPortrait() {
    TestMohTheme {
        Surface {
            SettingsScreen(onBackClick = {})
        }
    }
}
