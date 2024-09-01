package com.kvrae.easykitchen.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.kvrae.easykitchen.data.dto.asDto
import com.kvrae.easykitchen.data.models.Meal
import com.kvrae.easykitchen.ui.components.MealByAreaAnsCategoryCard

@Composable
fun MealsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    meals : List<Meal>
) {
    if (meals.isEmpty()) {
        return
    }
    LazyColumn(
        modifier = modifier

    ) {
        items(
            count = meals.size,
            key = { index -> meals[index].id?.oid ?: index }
        ) { index ->
            MealByAreaAnsCategoryCard(
                meal = meals[index].asDto(),
            )
        }
    }
}