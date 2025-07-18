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

/**
 * Defines the routes (unique identifiers) for each screen in the onboarding flow.
 * Using an object with constants helps prevent typos and makes navigation more robust.
 */
object OnboardingRoutes {
    const val SCREEN_ONE = "onboarding_screen_one"
    const val SCREEN_TWO = "onboarding_screen_two"
    const val SCREEN_THREE = "onboarding_screen_three"
    const val SCREEN_FOUR = "onboarding_screen_four"
}

/**
 * The main navigation host for the onboarding flow.
 * This Composable defines the navigation graph, mapping routes to specific Composable screens.
 *
 * @param navController The NavHostController instance, which manages the navigation stack.
 * It's used to navigate between different screens.
 * @param modifier Modifier to be applied to the NavHost. Allows for layout adjustments.
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // NavHost is the core of Compose Navigation. It takes a NavHostController,
    // a starting destination, and a lambda to define the navigation graph.
    NavHost(
        navController = navController,
        startDestination = OnboardingRoutes.SCREEN_ONE, // Specifies the first screen to display when the app launches
        modifier = modifier // Apply any modifiers passed to the NavHost
    ) {
        // Define the route and content for Onboarding Screen One.
        // When this route is active, OnboardingScreenOne will be displayed.
        composable(OnboardingRoutes.SCREEN_ONE) {
            OnboardingScreenOne(
                // Pass navigation actions as lambdas to the screen Composable.
                // When "Sign In" or "Sign Up" buttons are clicked, navigate to SCREEN_TWO.
                onSignInClick = { navController.navigate(OnboardingRoutes.SCREEN_TWO) },
                onSignUpClick = { navController.navigate(OnboardingRoutes.SCREEN_TWO) }
            )
        }
        // Define the route and content for Onboarding Screen Two.
        composable(OnboardingRoutes.SCREEN_TWO) {
            OnboardingScreenTwo(
                // Navigate to SCREEN_THREE from this screen.
                onSignInClick = { navController.navigate(OnboardingRoutes.SCREEN_THREE) },
                onSignUpClick = { navController.navigate(OnboardingRoutes.SCREEN_THREE) }
            )
        }
        // Define the route and content for Onboarding Screen Three.
        composable(OnboardingRoutes.SCREEN_THREE) {
            OnboardingScreenThree(
                // Navigate to SCREEN_FOUR from this screen.
                onSignInClick = { navController.navigate(OnboardingRoutes.SCREEN_FOUR) },
                onSignUpClick = { navController.navigate(OnboardingRoutes.SCREEN_FOUR) }
            )
        }
        // Define the route and content for Onboarding Screen Four.
        // This is the last onboarding screen.
        composable(OnboardingRoutes.SCREEN_FOUR) {
            OnboardingScreenFour(
                // For the last screen, these actions would typically navigate to the main part of the app
                // or a login/registration flow outside of onboarding.
                onSignInClick = { /* TODO: Navigate to Main App or Login Screen */ },
                onSignUpClick = { /* TODO: Navigate to Main App or Registration Screen */ }
            )
        }
    }
}
