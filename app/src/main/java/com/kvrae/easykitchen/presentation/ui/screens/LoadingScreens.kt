package com.kvrae.easykitchen.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kvrae.easykitchen.R
import com.kvrae.easykitchen.presentation.ui.components.MealCoveredImageShimmer


@Composable
fun CircularLoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
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

@Composable
fun LottieAnimation(
    modifier: Modifier = Modifier,
    iterations: Int = LottieConstants.IterateForever,
    rawRes: Int = R.raw.food_loading,
) {
    val restart = iterations == 1
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(rawRes))
    LottieAnimation(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        composition = composition,
        iterations = iterations,
        restartOnPlay = restart,

        )
}
