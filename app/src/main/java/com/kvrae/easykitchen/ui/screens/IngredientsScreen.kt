package com.kvrae.easykitchen.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kvrae.easykitchen.data.dto.IngredientDto
import com.kvrae.easykitchen.data.dto.MealDetailDto
import com.kvrae.easykitchen.data.dto.asDto
import com.kvrae.easykitchen.data.models.remote.IngredientResponse
import com.kvrae.easykitchen.data.models.remote.MealResponse
import com.kvrae.easykitchen.ui.components.IngredientCard
import com.valentinilk.shimmer.shimmer

@Composable
fun IngredientsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onIngredientClick: (String) -> Unit,
    ingredientResponses: List<IngredientResponse>,
    meals : List<MealResponse>
) {
    val ingredientsBasket = remember {
        mutableStateListOf<IngredientDto>()
    }
    val mealsFound = remember {
        mutableStateListOf<MealDetailDto>()
    }
    Box(
        modifier = modifier
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
                        if (ingredientsBasket.contains(ingredientResponses[index].asDto())) {
                            ingredientsBasket.remove(ingredientResponses[index].asDto())
                        } else {
                            ingredientsBasket.add(ingredientResponses[index].asDto())
                        }
                    },
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(32.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .height(56.dp)
                .align(Alignment.BottomCenter)
            ,
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .shimmer()
                    .fillMaxSize(),
                onClick = { /*TODO*/ },
                content = {}
            )
            Text(text = "You have ${ingredientsBasket.size} ingredients available")
        }
    }

}