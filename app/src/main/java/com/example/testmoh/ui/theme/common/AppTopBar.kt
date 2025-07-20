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
import com.example.testmoh.ui.theme.CardBackgroundLight
import com.example.testmoh.ui.theme.PrimaryOrange
import com.example.testmoh.ui.theme.SuccessGreen
import com.example.testmoh.ui.theme.TextOnLight
import com.example.testmoh.ui.theme.TextPrimaryDark
import com.example.testmoh.util.Constants

@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    isConnected: Boolean = false,
    backgroundColor: Color = CardBackgroundLight
) {
    val contentColor = if (backgroundColor == Color.Black) TextPrimaryDark else TextOnLight

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(Constants.TOP_BAR_HEIGHT)
            .background(backgroundColor)
            .padding(horizontal = Constants.CONTENT_HORIZONTAL_PADDING),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = contentColor
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor,
                modifier = if (onBackClick == null) Modifier.align(Alignment.CenterStart) else Modifier.align(Alignment.Center)
            )
        }

        if (isConnected) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(SuccessGreen, shape = MaterialTheme.shapes.small)
            )
        } else if (title.startsWith("Commande #")) {
            Button(
                onClick = { },
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
