package com.example.testmoh.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A custom Button Composable designed for the POS screen.
 * Ensures consistent sizing, text size, and colors.
 * Now supports an optional border for transparent buttons and improved text visibility.
 *
 * @param text The text to display on the button.
 * @param onClick The action to perform when the button is clicked.
 * @param modifier Modifier to be applied to the Button.
 * @param backgroundColor The background color of the button.
 * @param textColor The color of the button text.
 * @param border Optional BorderStroke for transparent buttons with a border.
 */
@Composable
fun PosButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    textColor: Color = Color.White,
    border: BorderStroke? = null
) {
    Button(
        onClick = onClick,
        // Increased button size slightly for better touch target and text fit
        modifier = modifier.size(width = 100.dp, height = 35.dp), // Adjusted size for better visual
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        border = border,
        // Increased internal padding for text visibility
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        // Significantly increased font size for better visibility
        Text(text, fontSize = 12.sp, color = textColor) // Increased from 7.sp to 12.sp
    }
}
