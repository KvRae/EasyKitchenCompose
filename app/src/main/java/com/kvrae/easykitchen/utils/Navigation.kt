package com.kvrae.easykitchen.utils

import SearchBarLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kvrae.easykitchen.data.dto.asMealDetail
import com.kvrae.easykitchen.logic.CategoryViewModel
import com.kvrae.easykitchen.logic.IngredientViewModel
import com.kvrae.easykitchen.logic.MealsViewModel
import com.kvrae.easykitchen.ui.screens.MainScreen
import com.kvrae.easykitchen.ui.screens.MealDetailsScreen
import com.kvrae.easykitchen.ui.screens.SplashScreen

// setting the navigation composable
@Composable
fun App(
    mealsViewModel: MealsViewModel,
    ingredientsViewModel: IngredientViewModel,
    categoryViewModel: CategoryViewModel,
) {
    Navigation(
        mealsViewModel = mealsViewModel,
        ingredientsViewModel = ingredientsViewModel,
        categoryViewModel = categoryViewModel,
    )
}

// setting navigator class
sealed class Screen(
    val route: String,
) {
    data object SplashScreen : Screen(SPLASH_SCREEN_ROUTE)

    data object MainScreen : Screen(MAIN_SCREEN_ROUTE)

    data object SearchLayout : Screen(SEARCH_BAR_LAYOUT_ROUTE)

    data object MealDetailsScreen : Screen(MEAL_DETAILS_SCREEN_ROUTE)
}

// setting the navigation composable
@Composable
fun Navigation(
    mealsViewModel: MealsViewModel,
    ingredientsViewModel: IngredientViewModel,
    categoryViewModel: CategoryViewModel,
) {
    val isNetworkOn = rememberNetworkConnectivity()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(
                navController = navController,
            )
        }
        composable(Screen.MainScreen.route) {
            MainScreen(
                navController = navController,
                isNetworkOn = isNetworkOn,
                mealsViewModel = mealsViewModel,
                ingredientViewModel = ingredientsViewModel,
                categoryViewModel = categoryViewModel,
            )
        }
        composable("${Screen.MealDetailsScreen.route}/{mealId}") { backStackEntry ->

            val mealId = backStackEntry.arguments?.getString("mealId")
            val meal = mealsViewModel.findMealById(mealId)

            if (meal != null) {
                MealDetailsScreen(
                    navController = navController,
                    meal = meal.asMealDetail(),
                )
            }
        }
        composable(Screen.SearchLayout.route) {
            SearchBarLayout(
                navController = navController,
                items = emptyList(),
            )
        }
    }
}


fun NavController.popThenNavigateTo(
    navigateRoute: String,
    popRoute: String,
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

const val SEARCH_BAR_LAYOUT_ROUTE = "search"
