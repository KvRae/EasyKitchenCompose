package com.kvrae.easykitchen.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.kvrae.easykitchen.utils.MEAL_DETAILS_SCREEN_ROUTE
import com.kvrae.easykitchen.utils.navigateTo

@Composable
fun MealsScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Meals Screen")
        Button(onClick = {
            navController.navigateTo(MEAL_DETAILS_SCREEN_ROUTE)
        }) {
            Text(text = "Go to Meal Details")
        }
    }
}