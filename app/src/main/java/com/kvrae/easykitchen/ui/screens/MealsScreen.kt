package com.kvrae.easykitchen.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.kvrae.easykitchen.data.dto.asDto
import com.kvrae.easykitchen.data.models.remote.MealResponse
import com.kvrae.easykitchen.ui.components.MealByAreaAnsCategoryCard
import com.kvrae.easykitchen.utils.Screen

@Composable
fun MealsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    mealResponses : List<MealResponse>
) {
    if (mealResponses.isEmpty()) {
        return
    }
    LazyColumn(
        modifier = modifier

    ) {
        items(
            count = mealResponses.size,
            key = { index -> mealResponses[index].idResponse?.oid ?: index }
        ) { index ->
            MealByAreaAnsCategoryCard(
                meal = mealResponses[index].asDto(),
                onMealClick = {
                    navController.navigate("${Screen.MealDetailsScreen.route}/${it}")
                }
            )
        }
    }
}