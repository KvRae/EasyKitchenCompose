package com.kvrae.easykitchen.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.kvrae.easykitchen.data.dto.MealDetailDto


@Composable
fun MealDetailsScreen(
    navController: NavController,
    meal : MealDetailDto
) {
    SubcomposeAsyncImage(
        model = meal.image.orEmpty(),
        contentDescription =  meal.name.orEmpty(),
        loading = {
            CircularProgressIndicator()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}