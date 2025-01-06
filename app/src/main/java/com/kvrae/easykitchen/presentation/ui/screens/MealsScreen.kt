package com.kvrae.easykitchen.presentation.ui.screens

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kvrae.easykitchen.data.remote.dto.MealResponse
import com.kvrae.easykitchen.data.remote.dto.asDto
import com.kvrae.easykitchen.presentation.logic.MealsViewModel
import com.kvrae.easykitchen.presentation.ui.components.MealImageCoveredCard
import com.kvrae.easykitchen.utils.Screen

@Composable
fun MealsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    mealsViewModel: MealsViewModel
) {
    val mealResponses = mealsViewModel.meals.collectAsState().value
    var refreshing by remember { mutableStateOf(false) }
    val refreshingState = rememberSwipeRefreshState(isRefreshing = refreshing)

    SwipeRefresh(
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
        MealScreenContent(
            modifier = modifier,
            mealList = mealResponses,
            onMealClick = { mealId ->
                navController.navigate("${Screen.MealDetailsScreen.route}/$mealId")
            },
            onFavoriteClick = { mealId ->
//                mealsViewModel.toggleFavorite(mealId)
            }
        )
        if (mealsViewModel.isLoading || refreshing) MealsImageCoveredListLoad(modifier)
    }
}

@Composable
fun MealScreenContent(
    modifier: Modifier = Modifier,
    mealList: List<MealResponse>,
    onMealClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            count = mealList.size,
            key = { index -> mealList[index].idResponse ?: index },
        ) { index ->
            MealImageCoveredCard(
                meal = mealList[index].asDto(),
                onMealClick = {
                    onMealClick(mealList[index].idResponse ?: "")
                },
                onFavoriteClick = {
                    onFavoriteClick(mealList[index].idResponse ?: "")
                }
            )
        }
    }
}

