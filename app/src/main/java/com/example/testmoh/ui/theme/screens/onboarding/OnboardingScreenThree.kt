package com.example.testmoh.ui.theme.screens.onboarding

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
import androidx.compose.material3.Surface // Ensure this import is present
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
import com.example.testmoh.util.Constants
import com.example.testmoh.util.GifImage

@Composable
fun OnboardingScreenThree(
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .size(width = Constants.POS_SCREEN_WIDTH, height = Constants.POS_SCREEN_HEIGHT) // Use new constants
            .background(Color(0xFF1A1A2E)) // Fallback dark background
    ) {
        // Background GIF for screen three
        GifImage(
            drawableRes = R.drawable.onboarding_anim_three,
            modifier = Modifier.fillMaxSize()
        )

        // Content Column: This will hold all the UI elements.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 4.dp), // Adjusted vertical padding for new scale
            horizontalAlignment = Alignment.CenterHorizontally, // Center the entire content block horizontally
            verticalArrangement = Arrangement.Top // Start arranging from the top
        ) {
            // Spacer to push content down
            Spacer(modifier = Modifier.weight(1.0f)) // Adjusted weight for new scale

            // Main Title and Sub-text block
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Constants.CONTENT_START_PADDING, end = Constants.CONTENT_START_PADDING), // Apply consistent left/right padding
                horizontalAlignment = Alignment.Start, // Align text to the start (left)
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Fast-Food ?\nEncore Plus Rapide.", // Original text
                    color = Color.White,
                    fontSize = 30.sp, // Adjusted font size for 230dp width
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start, // Left align text
                    lineHeight = 34.sp
                )

                // Spacer between main title and sub-message
                Spacer(modifier = Modifier.height(8.dp)) // Adjusted gap

                Text(
                    text = "Touchez un tag, accédez au menu, commandez et récupérez – sans attendre.", // Original text
                    color = Color.LightGray,
                    fontSize = 14.sp, // Adjusted font size
                    textAlign = TextAlign.Start // Left align text
                )
            }

            // Spacer to control space between text and buttons
            Spacer(modifier = Modifier.height(24.dp)) // Adjusted fixed height for larger gap

            // Buttons in a Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Constants.CONTENT_START_PADDING, end = Constants.CONTENT_START_PADDING, bottom = 4.dp), // Apply consistent left/right padding
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

                Spacer(modifier = Modifier.width(12.dp)) // Space between buttons

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
            Spacer(modifier = Modifier.weight(0.1f))

            // "Je suis professionnel" text
            Text(
                text = "Je suis professionnel",
                color = Color.LightGray,
                fontSize = 14.sp, // Adjusted font size
                textAlign = TextAlign.Start, // Align with buttons
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Constants.CONTENT_START_PADDING) // Apply consistent left padding
            )

            // Spacer between "Je suis professionnel" and "Licensed by The Tag"
            Spacer(modifier = Modifier.height(4.dp)) // Adjusted gap

            // "Licensed by The Tag" text at the very bottom
            Text(
                text = "Licensed by The Tag",
                color = Color.Gray,
                fontSize = 12.sp, // Adjusted font size
                textAlign = TextAlign.Center, // Centered at the bottom
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp) // Adjusted bottom padding
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 230, heightDp = 315) // Preview with new dimensions
@Composable
fun PreviewOnboardingScreenThreePOS() {
    TestMohTheme {
        Surface {
            OnboardingScreenThree()
        }
    }
}