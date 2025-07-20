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
import androidx.compose.material3.Surface
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
import com.example.testmoh.ui.theme.BackgroundDark
import com.example.testmoh.ui.theme.TextPrimaryDark
import com.example.testmoh.ui.theme.TextSecondaryDark
import com.example.testmoh.ui.theme.ButtonBorderColor
import com.example.testmoh.ui.theme.TextOnLight
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
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        GifImage(
            drawableRes = R.drawable.onboarding_anim_three,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = Constants.SCREEN_VERTICAL_PADDING * 2),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.weight(1.0f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Constants.CONTENT_HORIZONTAL_PADDING * 2),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Fast-Food ?\nEncore Plus Rapide.",
                    color = TextPrimaryDark,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    lineHeight = 48.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Touchez un tag, accédez au menu, commandez et récupérez – sans attendre.",
                    color = TextSecondaryDark,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.height(64.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = Constants.CONTENT_HORIZONTAL_PADDING * 2,
                        end = Constants.CONTENT_HORIZONTAL_PADDING * 2,
                        bottom = 16.dp
                    ),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PosButton(
                    text = "S'inscrire",
                    onClick = onSignUpClick,
                    backgroundColor = Color.White,
                    textColor = TextOnLight,
                    fontSize = 18
                )

                Spacer(modifier = Modifier.width(24.dp))

                PosButton(
                    text = "Se connecter",
                    onClick = onSignInClick,
                    backgroundColor = Color.Transparent,
                    textColor = TextPrimaryDark,
                    border = BorderStroke(2.dp, ButtonBorderColor),
                    fontSize = 18
                )
            }

            Spacer(modifier = Modifier.weight(0.1f))

            Text(
                text = "Je suis professionnel",
                color = TextSecondaryDark,
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Constants.CONTENT_HORIZONTAL_PADDING * 2)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Licensed by The Tag",
                color = Color.Gray,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun PreviewOnboardingScreenThreePOS() {
    TestMohTheme {
        Surface {
            OnboardingScreenThree()
        }
    }
}
