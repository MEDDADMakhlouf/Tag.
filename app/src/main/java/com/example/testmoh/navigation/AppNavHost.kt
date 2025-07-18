package com.example.testmoh.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testmoh.onboarding.OnboardingScreenFour
import com.example.testmoh.onboarding.OnboardingScreenOne
import com.example.testmoh.onboarding.OnboardingScreenThree
import com.example.testmoh.onboarding.OnboardingScreenTwo

object OnboardingRoutes {
    const val SCREEN_ONE = "onboarding_screen_one"
    const val SCREEN_TWO = "onboarding_screen_two"
    const val SCREEN_THREE = "onboarding_screen_three"
    const val SCREEN_FOUR = "onboarding_screen_four"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = OnboardingRoutes.SCREEN_ONE,
        modifier = modifier
    ) {
        composable(OnboardingRoutes.SCREEN_ONE) {
            OnboardingScreenOne(
                onSignInClick = { navController.navigate(OnboardingRoutes.SCREEN_TWO) },
                onSignUpClick = { navController.navigate(OnboardingRoutes.SCREEN_TWO) }
            )
        }
        composable(OnboardingRoutes.SCREEN_TWO) {
            OnboardingScreenTwo(
                onSignInClick = { navController.navigate(OnboardingRoutes.SCREEN_THREE) },
                onSignUpClick = { navController.navigate(OnboardingRoutes.SCREEN_THREE) }
            )
        }
        composable(OnboardingRoutes.SCREEN_THREE) {
            OnboardingScreenThree(
                onSignInClick = { navController.navigate(OnboardingRoutes.SCREEN_FOUR) },
                onSignUpClick = { navController.navigate(OnboardingRoutes.SCREEN_FOUR) }
            )
        }
        composable(OnboardingRoutes.SCREEN_FOUR) {
            OnboardingScreenFour(
                onSignInClick = { /* TODO: Navigate to Main App or Login Screen */ },
                onSignUpClick = { /* TODO: Navigate to Main App or Registration Screen */ }
            )
        }
    }
}