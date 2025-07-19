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
        // Adjusted button size for the new 230dp width
        modifier = modifier.size(width = 100.dp, height = 40.dp), // Slightly larger than before for better touch target
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        border = border,
        // Adjusted internal padding for text visibility
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    ) {
        // Adjusted font size for better visibility on the new dp scale
        Text(text, fontSize = 14.sp, color = textColor) // Adjusted from 12.sp
    }
}
