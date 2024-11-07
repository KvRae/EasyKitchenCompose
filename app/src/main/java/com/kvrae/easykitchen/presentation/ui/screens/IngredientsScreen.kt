package com.kvrae.easykitchen.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kvrae.easykitchen.data.remote.dto.asDto
import com.kvrae.easykitchen.presentation.logic.IngredientViewModel
import com.kvrae.easykitchen.presentation.logic.MealsViewModel
import com.kvrae.easykitchen.presentation.ui.components.IngredientCard


@Composable
fun IngredientsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onIngredientClick: (String) -> Unit,
    ingredientsViewModel: IngredientViewModel,
    mealsViewModel: MealsViewModel
) {
    var isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    SwipeRefresh(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        state =  swipeRefreshState,
        onRefresh = {
            isRefreshing = true
            ingredientsViewModel.fetchIngredients()
            isRefreshing = false
        }
    ) {
    val ingredientResponses = ingredientsViewModel.ingredients.collectAsState().value

    if (ingredientResponses.isEmpty()) NoDataScreen()
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .padding(8.dp),
    ) {
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter),
            columns = StaggeredGridCells.Adaptive(200.dp)
        ) {
            items(count = ingredientResponses.size,
                key = { index -> index }
                ) { index ->
                IngredientCard(
                    ingredient = ingredientResponses[index].asDto(),
                    onIngredientClick = {
                    },
                )
            }
        }
    }

    if (ingredientsViewModel.isLoading) CircularLoadingScreen()

    }

}
