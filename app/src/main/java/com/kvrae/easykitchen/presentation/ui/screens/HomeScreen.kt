package com.kvrae.easykitchen.presentation.ui.screens


import SearchBarField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kvrae.easykitchen.R
import com.kvrae.easykitchen.data.remote.dto.CategoryResponse
import com.kvrae.easykitchen.data.remote.dto.MealResponse
import com.kvrae.easykitchen.data.remote.dto.asDto
import com.kvrae.easykitchen.presentation.logic.CategoryViewModel
import com.kvrae.easykitchen.presentation.logic.MealsViewModel
import com.kvrae.easykitchen.presentation.ui.components.CategoryCard
import com.kvrae.easykitchen.presentation.ui.components.HorizontalList
import com.kvrae.easykitchen.presentation.ui.components.MealByAreaAnsCategoryCard
import com.kvrae.easykitchen.presentation.ui.components.MealCard
import com.kvrae.easykitchen.utils.Screen
import com.kvrae.easykitchen.utils.getUserLocation

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    mealsViewModel: MealsViewModel,
    categoriesViewModel: CategoryViewModel,
) {
    val mealResponses = mealsViewModel.meals.collectAsState().value
    val categories by categoriesViewModel.categories.collectAsState()

    var refreshing by remember { mutableStateOf(false) }
    val refreshingState = rememberSwipeRefreshState(isRefreshing = refreshing)

    SwipeRefresh(
        modifier = Modifier.statusBarsPadding(),
        swipeEnabled = !mealsViewModel.isLoading,
        state = refreshingState,
        onRefresh = {
            refreshing = true
            categoriesViewModel.fetchCategories()
            mealsViewModel.fetchMeals()
            refreshing = false
        }
    ) {
        if (!mealsViewModel.isLoading && mealResponses.isEmpty() && categories.isEmpty()) NoDataScreen()

        if (mealResponses.isNotEmpty() && categories.isNotEmpty()) HomeScreenContent(
            modifier = modifier,
            navController = navController,
            mealsViewModel = mealsViewModel,
            categories = categories,
            mealResponses = mealResponses,
        )

        if (mealsViewModel.isLoading || categoriesViewModel.isLoading) LottieAnimation(
            rawRes = R.raw.food_loading,
        )
    }
}


@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    mealsViewModel: MealsViewModel,
    categories: List<CategoryResponse>,
    mealResponses: List<MealResponse>,
) {
    val scrollState = rememberScrollState()
    val items = mealsViewModel.mealsByTime.collectAsState().value
    Column(
        modifier =
        modifier
            .fillMaxSize(1f)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        SearchBarField(
            modifier = Modifier,
            navController = navController,
            placeholder = stringResource(id = R.string.search_meal),
        )
        LazyRow {
            items(categories.size, key = { index -> index }) { index ->
                CategoryCard(
                    category = categories[index].asDto(),
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        HorizontalList(
            title = "Ideas for ${mealsViewModel.mealTime}",
            content = {
                LazyRow {
                    items(items.size, key = { index -> index }) { index ->
                        MealCard(
                            meal = items[index].asDto(),
                            onMealClick = {
                                navController
                                    .navigate("${Screen.MealDetailsScreen.route}/$it")
                            },
                        )
                    }
                }
            },
        )
        Spacer(modifier = Modifier.weight(1f))
        HorizontalList(
            title = "Popular in ${getUserLocation()}",
            content = {},
        )
        LazyRow {
            val items = mealResponses.filter { it.asDto().category == "Dessert" }
            items(items.size, key = { index -> index }) { index ->
                MealByAreaAnsCategoryCard(
                    meal = items[index].asDto(),
                    onMealClick = {
                        navController
                            .navigate("${Screen.MealDetailsScreen.route}/$it")
                    },
                )
            }
        }
    }
}


