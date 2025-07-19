package com.example.testmoh.ui.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmoh.util.Constants

@Composable
fun PosButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    textColor: Color = Color.White,
    border: BorderStroke? = null,
    fontSize: Int = 18 // Default font size for these buttons
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(160.dp) // Adjusted width for design
            .height(Constants.BUTTON_HEIGHT),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        border = border,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Text(text, fontSize = fontSize.sp, fontWeight = FontWeight.SemiBold, color = textColor)
    }
}
