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
import com.example.testmoh.util.GifImage

@Composable
fun OnboardingScreenFour(
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    val contentStartPadding = 24.dp

    Box(
        modifier = modifier
            .size(width = 58.dp, height = 80.dp)
            .background(Color(0xFF1A1A2E))
    )
            {
                GifImage(
                    drawableRes = R.drawable.onboarding_anim_four,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.weight(1.5f))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = contentStartPadding, end = 2.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Restaurant ?Plus Besoin\nD'attendre.",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            lineHeight = 28.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Consultez le menu, appelez un serveur, demandez l'addition... tout depuis votre téléphone.",
                            color = Color.LightGray,
                            fontSize = 10.sp,
                            textAlign = TextAlign.Start
                        )
                    }

                    Spacer(modifier = Modifier.height(60.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = contentStartPadding, end = 2.dp, bottom = 1.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PosButton(
                            text = "S'inscrire",
                            onClick = onSignUpClick,
                            backgroundColor = Color.White,
                            textColor = Color.Black
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        PosButton(
                            text = "Se connecter",
                            onClick = onSignInClick,
                            backgroundColor = Color.Transparent,
                            textColor = Color.White,
                            border = BorderStroke(1.dp, Color.White)
                        )
                    }

                    Spacer(modifier = Modifier.weight(0.01f))

                    Text(
                        text = "Je suis professionnel",
                        color = Color.LightGray,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = contentStartPadding)
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "Licensed by The Tag",
                        color = Color.Gray,
                        fontSize = 8.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    )
                }
            }
}

@Preview(showBackground = true, widthDp = 58, heightDp = 80)
@Composable
fun PreviewOnboardingScreenFourPOS() {
    TestMohTheme {
        Surface {
            OnboardingScreenFour()
        }
    }
}