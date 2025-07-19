package com.example.testmoh.ui.theme.screens.common



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
import androidx.compose.ui.res.painterResource // Import for painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.testmoh.R // Import R for drawable resources
import com.example.testmoh.ui.theme.TestMohTheme
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
                .size(width = Constants.POS_SCREEN_WIDTH, height = Constants.POS_SCREEN_HEIGHT)
                .background(Color.Black.copy(alpha = 0.8f))
                .padding(Constants.CONTENT_START_PADDING),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .width(180.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Using painterResource to load a drawable from res/drawable
                // You need to place a drawable file (e.g., ic_no_wifi.xml or no_wifi.png)
                // in your app/src/main/res/drawable folder.
                Icon(
                    painter = painterResource(id = R.drawable.no_wifi), // Replace 'ic_no_wifi' with your drawable's name
                    contentDescription = "No Connection",
                    tint = Color.Black,
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "On dirait que vous avez perdu la connexion.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Veuillez vérifier votre réseau et réessayer.",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F3460)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Vérifier la connexion",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 230, heightDp = 315)
@Composable
fun PreviewConnectionErrorDialog() {
    TestMohTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.DarkGray) {
            ConnectionErrorDialog(onDismiss = {})
        }
    }
}
