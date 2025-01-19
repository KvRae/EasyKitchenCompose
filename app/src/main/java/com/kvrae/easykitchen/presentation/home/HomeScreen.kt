package com.kvrae.easykitchen.presentation.home


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
import com.kvrae.easykitchen.presentation.miscellaneous.components.CategoryCard
import com.kvrae.easykitchen.presentation.miscellaneous.components.HorizontalList
import com.kvrae.easykitchen.presentation.miscellaneous.components.MealByAreaAnsCategoryCard
import com.kvrae.easykitchen.presentation.miscellaneous.components.MealCard
import com.kvrae.easykitchen.presentation.miscellaneous.screens.LottieAnimation
import com.kvrae.easykitchen.presentation.miscellaneous.screens.NoDataScreen
import com.kvrae.easykitchen.utils.Screen
import com.kvrae.easykitchen.utils.getMealTime
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,

) {
    val viewModel = getViewModel<HomeViewModel>()
    val homeState by viewModel.homeState.collectAsState()
    var refreshing by remember { mutableStateOf(false) }
    val refreshingState = rememberSwipeRefreshState(isRefreshing = refreshing)

    SwipeRefresh(
        modifier = Modifier.statusBarsPadding(),
        state = refreshingState,
        onRefresh = {
            refreshing = true
            viewModel.getData()
            refreshing = false
        }
    ) {
        when(homeState){
            is HomeState.Loading -> LottieAnimation(
                rawRes = R.raw.food_loading,
            )
            is HomeState.Success -> HomeScreenContent(
                modifier = modifier,
                navController = navController,
                meals = (homeState as HomeState.Success).meals,
                categories = (homeState as HomeState.Success).categories
            )
            is HomeState.Error -> NoDataScreen(
                message = (homeState as HomeState.Error).message
            )
        }






    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    categories : List<CategoryResponse>,
    meals : List<MealResponse>
) {
    Column(
        modifier =
        modifier
            .fillMaxSize(1f)
            .verticalScroll(rememberScrollState()),
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
            title = "Ideas for ${getMealTime()}",
            content = {
                LazyRow {
                    val items = meals.filter { it.asDto().category == "Dessert" }
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
            title = "Some Dessert Ideas",
            content = {
                LazyRow {
                    val desserts = meals.filter { it.asDto().category == "Dessert" }
                    items(desserts.size, key = { index -> index }) { index ->
                        MealByAreaAnsCategoryCard(
                            meal = desserts[index].asDto(),
                            onMealClick = {
                                navController
                                    .navigate("${Screen.MealDetailsScreen.route}/$it")
                            },
                        )
                    }
                }
            },
        )
    }
}