package com.example.testmoh.ui.theme.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmoh.ui.theme.CardBackgroundLight // Default white background
import com.example.testmoh.ui.theme.PrimaryOrange
import com.example.testmoh.ui.theme.SuccessGreen
import com.example.testmoh.ui.theme.TextOnLight // Assuming this is a dark color for light backgrounds (e.g., Black)
import com.example.testmoh.ui.theme.TextPrimaryDark
import com.example.testmoh.util.Constants

@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null, // Made nullable for optional back arrow
    isConnected: Boolean = false, // For connection status indicator
    backgroundColor: Color = CardBackgroundLight // Default to CardBackgroundLight (white)
) {
    // Determine the text/icon color based on the background color's perceived lightness
    // This is a simple heuristic; for more robust check, you'd calculate luminance.
    val contentColor = if (backgroundColor == Color.Black) TextPrimaryDark else TextOnLight

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(Constants.TOP_BAR_HEIGHT)
            .background(backgroundColor) // Use the passed background color
            .padding(horizontal = Constants.CONTENT_HORIZONTAL_PADDING),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
            // Conditionally show back arrow
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = contentColor // Tint based on background
                    )
                }
                Spacer(modifier = Modifier.width(8.dp)) // Spacer after back arrow
            }
            // Title is centered within its Box, but its starting position will be affected by the IconButton's presence
            // To truly left-align when no back button, we adjust the weight and alignment.
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor, // Color based on background
                // Adjust modifier for alignment based on back button presence
                modifier = if (onBackClick == null) Modifier.align(Alignment.CenterStart) else Modifier.align(Alignment.Center)
            )
        }

        // Placeholder for connection status or "Payer" button
        if (isConnected) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(SuccessGreen, shape = MaterialTheme.shapes.small) // Use SuccessGreen for connected
            )
        } else if (title.startsWith("Commande #")) { // Example: If it's the order details screen
            Button(
                onClick = { /* Handle Pay click */ },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                shape = MaterialTheme.shapes.small,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("Payer", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppTopBarWhite() {
    AppTopBar(title = "Produits", onBackClick = {}, isConnected = true, backgroundColor = Color.White)
}

@Preview(showBackground = true)
@Composable
fun PreviewAppTopBarBlack() {
    AppTopBar(title = "Accueil", onBackClick = null, isConnected = true, backgroundColor = Color.Black)
}
