package com.kvrae.easykitchen.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kvrae.easykitchen.data.dto.asDto
import com.kvrae.easykitchen.data.models.Ingredient
import com.kvrae.easykitchen.ui.components.IngredientCard

@Composable
fun IngredientsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onIngredientClick: (String) -> Unit,
    ingredients: List<Ingredient>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Adaptive(200.dp)) {
            items(count = ingredients.size,
                key = { index -> index }
                ) { index ->
                IngredientCard(
                    ingredient = ingredients[index].asDto(),
                    onIngredientClick = onIngredientClick,
                )
            }
        }

    }

}