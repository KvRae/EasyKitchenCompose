package com.kvrae.easykitchen.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kvrae.easykitchen.ui.components.MealCoveredImageShimmer

@Composable
fun CircularLoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()

    }
}

@Composable
fun MealsImageCoveredListLoad(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(4) {
            MealCoveredImageShimmer()
        }
    }
}
