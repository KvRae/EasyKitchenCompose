package com.kvrae.easykitchen.ui.screens

import SearchBarLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.kvrae.easykitchen.data.dto.asDto
import com.kvrae.easykitchen.data.models.remote.CategoryResponse
import com.kvrae.easykitchen.data.models.remote.MealResponse
import com.kvrae.easykitchen.ui.components.CategoryCard
import com.kvrae.easykitchen.ui.components.HorizontalList
import com.kvrae.easykitchen.ui.components.MealByAreaAnsCategoryCard
import com.kvrae.easykitchen.ui.components.MealCard
import com.kvrae.easykitchen.utils.Screen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    mealResponses : List<MealResponse>,
    categories : List<CategoryResponse>,
) {
    val mealTime by rememberSaveable {
        mutableStateOf(getMealTime())
    }



    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize(1f)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        SearchBarLayout(
            modifier = Modifier,
            items = listOf("All", "Category", "Area"),
            placeholder = "Search for recipes",
            onTitleChange = {}
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
            title = "Ideas for $mealTime",
            content = {
                LazyRow {
                    val items = mealResponses.filter { it.asDto().category == getMealCategoryByTime() }
                    items(items.size, key = { index -> index }) { index ->
                        MealCard(
                            meal = items[index].asDto(),
                            onMealClick = {
                                navController
                                    .navigate("${Screen.MealDetailsScreen.route}/${it}")
                            }
                        )
                    }
                }
            }
        )

        Spacer(modifier = Modifier.weight(1f))
        HorizontalList(
            title = "Popular in ${getUserLocation()}",
            content = {}
        )
        LazyRow {
            val items = mealResponses.filter { it.asDto().category == "Dessert" }
            items(items.size, key = { index -> index }) { index ->
                MealByAreaAnsCategoryCard(
                    meal = items[index].asDto(),
                    onMealClick = {
                        navController
                            .navigate("${Screen.MealDetailsScreen.route}/${it}")
                    }
                )
            }
        }

    }
}

fun getMealTime(): String {
    val now = System.currentTimeMillis()
    val time = now % 86400000
    return when {
        time < 43200000 -> "Breakfast"
        time < 64800000 -> "Lunch"
        else -> "Dinner"
    }
}

fun getUserLocation(): String {
    return "New York"
}

fun getMealCategoryByTime(): String {
    val mealType = getMealTime()
    val breakfast = "Breakfast"
    val lunch = listOf("Lamb", "Pasta", "Chicken", "Beef", "Pork", "Goat").random()
    val dinner = listOf("Vegetarian", "Side", "Miscellaneous", "Seafood", "Starter", "Vegan").random()
    return when (mealType) {
        "Lunch" -> lunch
        "Dinner" -> dinner
        else -> breakfast
    }
}