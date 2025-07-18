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
import androidx.compose.material3.Surface // Added for consistency
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

/**
 * Composable function for the second onboarding screen, adapted for a small POS display.
 * It features a GIF background, original design text, and updated button styles.
 *
 * @param modifier Modifier to be applied to the root layout of the screen.
 * @param onSignInClick Lambda to be invoked when the "Se connecter" button is clicked.
 * @param onSignUpClick Lambda to be invoked when the "S'inscrire" button is clicked.
 */
@Composable
fun OnboardingScreenTwo(
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    // Define a consistent horizontal padding for all left-aligned components
    val contentStartPadding = 24.dp // Consistent with OnboardingScreenOne

    Box(
        modifier = modifier
            .size(width = 58.dp, height = 80.dp) // Target POS screen size
            .background(Color(0xFF1A1A2E)) // Fallback dark background
    ) {
        // Background GIF for screen two
        GifImage(
            drawableRes = R.drawable.onboarding_anim_two, // Reference to your GIF file in res/drawable
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
            // Spacer to push content down to start near the middle (consistent with page 1)
            Spacer(modifier = Modifier.weight(1.0f)) // Consistent with OnboardingScreenOne

            // Main Title and Sub-text block
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = contentStartPadding, end = 2.dp), // Apply consistent left padding
                horizontalAlignment = Alignment.Start, // Align text to the start (left)
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Un Simple Geste Suffit.", // Original text
                    color = Color.White,
                    fontSize = 25.sp, // Consistent font size
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start, // Left align text
                    lineHeight = 28.sp
                )

                // Spacer between main title and sub-message (increased gap)
                Spacer(modifier = Modifier.height(4.dp)) // Consistent with OnboardingScreenOne

                Text(
                    text = "Touchez un tag avec votre smartphone pour accéder instantanément aux services d’un établissement.", // Original text
                    color = Color.LightGray,
                    fontSize = 10.sp, // Consistent font size
                    textAlign = TextAlign.Start // Left align text
                )
            }

            // Spacer to control space between text and buttons (consistent with page 1)
            Spacer(modifier = Modifier.height(60.dp)) // Consistent with OnboardingScreenOne

            // Buttons in a Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
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
            Spacer(modifier = Modifier.weight(0.01f)) // Consistent with OnboardingScreenOne

            // "Je suis professionnel" text
            Text(
                text = "Je suis professionnel",
                color = Color.LightGray,
                fontSize = 10.sp,
                textAlign = TextAlign.Start, // Align with buttons
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = contentStartPadding) // Apply consistent left padding
            )

            // Spacer between "Je suis professionnel" and "Licensed by The Tag"
            Spacer(modifier = Modifier.height(2.dp)) // Consistent with OnboardingScreenOne

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
fun PreviewOnboardingScreenTwoPOS() {
    TestMohTheme {
        Surface {
            OnboardingScreenTwo()
        }
    }
}
