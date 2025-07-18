package com.example.testmoh.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface // Added for consistency as per your reminder
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
import com.example.testmoh.R
import com.example.testmoh.ui.components.PosButton
import com.example.testmoh.ui.theme.TestMohTheme
import com.example.testmoh.util.GifImage

@Composable
fun OnboardingScreenOne(
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    // Define a consistent horizontal padding for all left-aligned components
    val contentStartPadding = 24.dp // Our unified left margin

    Box(
        modifier = modifier
            .size(width = 58.dp, height = 80.dp) // Conceptual POS screen size
            .background(Color(0xFF1A1A2E)) // Fallback dark background
    ) {
        // Background Image for screen one
        GifImage(
            drawableRes = R.drawable.onboarding_bg_one,
            modifier = Modifier.fillMaxSize()
        )

        // Content Column: This will hold all the UI elements.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 2.dp), // Only vertical padding for the main column
            horizontalAlignment = Alignment.CenterHorizontally, // Center the entire content block horizontally
            verticalArrangement = Arrangement.Top // Start arranging from the top
        ) {
            // Spacer to push content down to roughly the 3rd "column"
            Spacer(modifier = Modifier.weight(1.0f)) // Adjusted weight to push content further down

            // Main Title and Sub-text block
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Allow text to take full width
                    .padding(start = contentStartPadding, end = 2.dp), // Apply consistent left padding
                horizontalAlignment = Alignment.Start, // Align text to the start (left)
                verticalArrangement = Arrangement.Center // Center text vertically within its column
            ) {
                Text(
                    text = "Touchez,\nDécouvrez,\nProfitez", // Original text
                    color = Color.White,
                    fontSize = 50.sp, // Increased font size (x2 from 25sp)
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start, // Left align text
                    lineHeight = 52.sp // Adjusted line height
                )
            }

            // Spacer to control space between text and buttons: Make this larger
            Spacer(modifier = Modifier.height(60.dp)) // Increased fixed height for larger gap

            // Buttons in a Row
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Allow buttons to take full width
                    .padding(start = contentStartPadding, end = 2.dp, bottom = 1.dp), // Apply consistent left padding
                horizontalArrangement = Arrangement.Start, // Align buttons to the start (left)
                verticalAlignment = Alignment.CenterVertically
            ) {
                // "S'inscrire" Button
                PosButton(
                    text = "S'inscrire",
                    onClick = onSignUpClick,
                    backgroundColor = Color.White,
                    textColor = Color.Black
                )

                Spacer(modifier = Modifier.width(8.dp)) // Space between buttons

                // "Se connecter" Button
                PosButton(
                    text = "Se connecter",
                    onClick = onSignInClick,
                    backgroundColor = Color.Transparent,
                    textColor = Color.White,
                    border = BorderStroke(1.dp, Color.White)
                )
            }

            // Spacer to push "Je suis professionnel" and "Licensed by The Tag" to the very bottom
            // This spacer now controls the space between buttons and "Je suis professionnel"
            Spacer(modifier = Modifier.weight(0.01f)) // Changed from 0.1f to 0.01f for minimal space

            // "Je suis professionnel" text
            Text(
                text = "Je suis professionnel",
                color = Color.LightGray,
                fontSize = 10.sp,
                textAlign = TextAlign.Start, // Align with buttons
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = contentStartPadding) // Removed bottom padding here
            )

            // NEW SPACER: Controls space between "Je suis professionnel" and "Licensed by The Tag"
            Spacer(modifier = Modifier.height(2.dp)) // Adjust this height (e.g., 2.dp, 4.dp, 0.dp) to control the gap

            // "Licensed by The Tag" text at the very bottom
            Text(
                text = "Licensed by The Tag",
                color = Color.Gray,
                fontSize = 8.sp,
                textAlign = TextAlign.Center, // Centered at the bottom
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 58, heightDp = 80)
@Composable
fun PreviewOnboardingScreenOnePOS() {
    TestMohTheme {
        Surface {
            OnboardingScreenOne()
        }
    }
}
