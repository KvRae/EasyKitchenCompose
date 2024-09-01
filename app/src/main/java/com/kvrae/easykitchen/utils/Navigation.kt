package com.kvrae.easykitchen.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kvrae.easykitchen.data.models.Category
import com.kvrae.easykitchen.data.models.Ingredient
import com.kvrae.easykitchen.data.models.Meal
import com.kvrae.easykitchen.ui.screens.MainScreen
import com.kvrae.easykitchen.ui.screens.MealDetailsScreen
import com.kvrae.easykitchen.ui.screens.SplashScreen

// setting the navigation composable
@Composable
fun App(
    meals : List<Meal>,
    ingredients : List<Ingredient>,
    categories : List<Category>
) {
    Navigation(
        meals = meals,
        ingredients = ingredients,
        categories = categories
    )
}

// setting navigator class
sealed class Screen(val route: String) {
    data object SplashScreen : Screen(SPLASH_SCREEN_ROUTE)
    data object MainScreen : Screen(MAIN_SCREEN_ROUTE)
    data object MealDetailsScreen : Screen(MEAL_DETAILS_SCREEN_ROUTE)
}

// setting the navigation composable
@Composable
fun Navigation(
    meals: List<Meal>,
    ingredients: List<Ingredient>,
    categories: List<Category>
) {
    val isNetworkOn = rememberNetworkConnectivity()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(
                navController = navController
            )
        }
        composable(Screen.MainScreen.route) {
            MainScreen(
                navController = navController,
                isNetworkOn = isNetworkOn,
                meals = meals,
                ingredients = ingredients,
                categories = categories
            )
        }
        composable(Screen.MealDetailsScreen.route) {
            MealDetailsScreen(
                navController = navController
            )
        }
    }
}

// Navigation Functionalities
fun NavController.navigateTo(route: String) {
    this.navigate(route)
}

fun NavController.popThenNavigateTo(
    navigateRoute: String,
    popRoute: String
) {
    this.navigate(navigateRoute) {
        popUpTo(popRoute) {
            inclusive = true
        }
    }
}

// Navigation routes
const val SPLASH_SCREEN_ROUTE = "splash"
const val MAIN_SCREEN_ROUTE = "main"
const val MEAL_DETAILS_SCREEN_ROUTE = "details"

const val MAIN_HOME_ROUTE = "Home"
const val MAIN_MEALS_ROUTE = "Meals"
const val MAIN_COMPOSE_ROUTE = "Compose"

