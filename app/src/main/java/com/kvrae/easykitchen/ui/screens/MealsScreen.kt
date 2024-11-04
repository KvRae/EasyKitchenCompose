package com.kvrae.easykitchen.ui.screens

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kvrae.easykitchen.data.dto.asDto
import com.kvrae.easykitchen.logic.MealsViewModel
import com.kvrae.easykitchen.ui.components.MealImageCoveredCard
import com.kvrae.easykitchen.utils.Screen

@Composable
fun MealsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    mealsViewModel: MealsViewModel
) {
    val mealResponses = mealsViewModel.meals.collectAsState().value

    val refreshScope = rememberCoroutineScope()

    var refreshing by remember { mutableStateOf(false) }

    val refreshingState = rememberSwipeRefreshState(isRefreshing = refreshing)

    SwipeRefresh (
        modifier = Modifier.statusBarsPadding(),
        swipeEnabled = !mealsViewModel.isLoading,
        state = refreshingState,
        onRefresh = {
            refreshing = true
            mealsViewModel.fetchMeals()
            refreshing = false
        }
    ) {


        if (mealResponses.isEmpty() && !mealsViewModel.isLoading) NoDataScreen()

        if (mealResponses.isNotEmpty() && !refreshing)
        LazyColumn(
            modifier = modifier,
        ) {
            items(
                count = mealResponses.size,
                key = { index -> mealResponses[index].idResponse ?: index },
            ) { index ->
                MealImageCoveredCard(
                    meal = mealResponses[index].asDto(),
                    onMealClick = {
                        navController.navigate("${Screen.MealDetailsScreen.route}/$it")
                    },
                )
            }
        }

        if (mealsViewModel.isLoading || refreshing) MealsImageCoveredListLoad(modifier)
    }

}

