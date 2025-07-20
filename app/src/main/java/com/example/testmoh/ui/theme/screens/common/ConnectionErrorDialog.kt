package com.example.testmoh.ui.theme.screens.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.testmoh.R
import com.example.testmoh.ui.theme.TestMohTheme
import com.example.testmoh.ui.theme.BackgroundDark
import com.example.testmoh.ui.theme.CardBackgroundLight
import com.example.testmoh.ui.theme.PrimaryBlue
import com.example.testmoh.ui.theme.TextOnLight
import com.example.testmoh.util.Constants

@Composable
fun ConnectionErrorDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.7f)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .width(320.dp)
                    .background(CardBackgroundLight, RoundedCornerShape(Constants.CARD_CORNER_RADIUS))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.no_wifi),
                    contentDescription = "No Connection",
                    tint = TextOnLight,
                    modifier = Modifier.size(72.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "On dirait que vous avez perdu la connexion.",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextOnLight,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Veuillez vérifier votre réseau et réessayer.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Constants.BUTTON_HEIGHT),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                    shape = RoundedCornerShape(Constants.CARD_CORNER_RADIUS - 2.dp)
                ) {
                    Text(
                        text = "Vérifier la connexion",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 600)
@Composable
fun PreviewConnectionErrorDialog() {
    TestMohTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.DarkGray) {
            ConnectionErrorDialog(onDismiss = {})
        }
    }
}
