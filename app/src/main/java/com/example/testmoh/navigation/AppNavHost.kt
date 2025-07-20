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
import com.example.testmoh.ui.theme.screens.common.ConnectionErrorDialog
import com.example.testmoh.ui.theme.screens.home.HomeScreen
import com.example.testmoh.ui.theme.screens.orderdetails.OrderDetailsScreen
import com.example.testmoh.ui.theme.screens.products.ProductsScreen
import com.example.testmoh.ui.theme.screens.settings.SettingsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.ONBOARDING_SCREEN_ONE,
        modifier = modifier
    ) {
        composable(AppRoutes.ONBOARDING_SCREEN_ONE) {
            OnboardingScreenOne(
                onSignInClick = { navController.navigate(AppRoutes.ONBOARDING_SCREEN_TWO) },
                onSignUpClick = { navController.navigate(AppRoutes.ONBOARDING_SCREEN_TWO) }
            )
        }
        composable(AppRoutes.ONBOARDING_SCREEN_TWO) {
            OnboardingScreenTwo(
                onSignInClick = { navController.navigate(AppRoutes.ONBOARDING_SCREEN_THREE) },
                onSignUpClick = { navController.navigate(AppRoutes.ONBOARDING_SCREEN_THREE) }
            )
        }
        composable(AppRoutes.ONBOARDING_SCREEN_THREE) {
            OnboardingScreenThree(
                onSignInClick = { navController.navigate(AppRoutes.ONBOARDING_SCREEN_FOUR) },
                onSignUpClick = { navController.navigate(AppRoutes.ONBOARDING_SCREEN_FOUR) }
            )
        }
        composable(AppRoutes.ONBOARDING_SCREEN_FOUR) {
            OnboardingScreenFour(
                onSignInClick = {
                    navController.navigate(AppRoutes.HOME_SCREEN) {
                        popUpTo(AppRoutes.ONBOARDING_SCREEN_ONE) { inclusive = true }
                    }
                },
                onSignUpClick = {
                    navController.navigate(AppRoutes.HOME_SCREEN) {
                        popUpTo(AppRoutes.ONBOARDING_SCREEN_ONE) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoutes.HOME_SCREEN) {
            HomeScreen(
                onNavigateToOrderDetails = { orderId -> navController.navigate("${AppRoutes.ORDER_DETAILS_SCREEN}/$orderId") },
                onNavigateToProducts = { navController.navigate(AppRoutes.PRODUCTS_SCREEN) },
                onNavigateToSettings = { navController.navigate(AppRoutes.SETTINGS_SCREEN) },
                onShowErrorDialog = { navController.navigate(AppRoutes.ERROR_MODAL) }
            )
        }
        composable("${AppRoutes.ORDER_DETAILS_SCREEN}/{orderId}") { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId")
            OrderDetailsScreen(
                orderId = orderId,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(AppRoutes.PRODUCTS_SCREEN) {
            ProductsScreen(
                onNavigateToHome = { navController.navigate(AppRoutes.HOME_SCREEN) },
                onNavigateToSettings = { navController.navigate(AppRoutes.SETTINGS_SCREEN) },
                onShowErrorDialog = { navController.navigate(AppRoutes.ERROR_MODAL) }
            )
        }
        composable(AppRoutes.SETTINGS_SCREEN) {
            SettingsScreen(
                onNavigateToHome = { navController.navigate(AppRoutes.HOME_SCREEN) },
                onNavigateToProducts = { navController.navigate(AppRoutes.PRODUCTS_SCREEN) },
                onShowErrorDialog = { navController.navigate(AppRoutes.ERROR_MODAL) }
            )
        }
        composable(AppRoutes.ERROR_MODAL) {
            ConnectionErrorDialog(
                onDismiss = { navController.popBackStack() }
            )
        }
    }
}
