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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff // Correct Import for WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface // Added for Preview
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.testmoh.ui.theme.TestMohTheme
import com.example.testmoh.util.Constants

/**
 * Composable for displaying a connection error dialog.
 * This dialog is designed to be a full-screen overlay within the POS device's conceptual size.
 *
 * @param onDismiss Lambda to be invoked when the dialog should be dismissed (e.g., by clicking the button).
 */
@Composable
fun ConnectionErrorDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Using a Dialog Composable from Compose UI, which handles the modal behavior.
    // It will overlay the content below it.
    Dialog(
        onDismissRequest = onDismiss, // Dismiss the dialog if clicked outside or back button pressed
        properties = DialogProperties(usePlatformDefaultWidth = false) // Crucial to control dialog width/height
    ) {
        // The content of the dialog, constrained to our POS screen size.
        Box(
            modifier = modifier
                .size(width = Constants.POS_SCREEN_WIDTH, height = Constants.POS_SCREEN_HEIGHT)
                .background(Color.Black.copy(alpha = 0.8f)) // Semi-transparent black overlay
                .padding(Constants.CONTENT_START_PADDING), // Padding from the edges of the POS screen
            contentAlignment = Alignment.Center // Center the dialog content within the box
        ) {
            Column(
                modifier = Modifier
                    .width(180.dp) // Fixed width for the dialog card itself
                    .background(Color.White, RoundedCornerShape(8.dp)) // White background with rounded corners
                    .padding(16.dp), // Internal padding for the dialog card
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.WifiOff, // WiFi off icon
                    contentDescription = "No Connection",
                    tint = Color.Black,
                    modifier = Modifier.size(48.dp) // Larger icon size
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
                        .width(150.dp) // Adjusted button width
                        .height(40.dp), // Adjusted button height
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F3460)), // Dark blue button
                    shape = RoundedCornerShape(8.dp) // Rounded corners for the button
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
