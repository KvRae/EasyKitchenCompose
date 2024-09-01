package com.kvrae.easykitchen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import com.kvrae.easykitchen.logic.CategoryViewModel
import com.kvrae.easykitchen.logic.IngredientViewModel
import com.kvrae.easykitchen.logic.MealsViewModel
import com.kvrae.easykitchen.ui.theme.EasyKitchenTheme
import com.kvrae.easykitchen.utils.App
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val mealsViewModel by inject<MealsViewModel> ()

    private val ingredientViewModel by inject<IngredientViewModel> ()

    private val categoryViewModel by inject<CategoryViewModel> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            EasyKitchenTheme {
                val ingredients = ingredientViewModel.ingredients.collectAsState()
                val meals = mealsViewModel.meals.collectAsState()
                val categories = categoryViewModel.categories.collectAsState()
                App(
                    meals = meals.value,
                    ingredients = ingredients.value,
                    categories  = categories.value
                )
            }
        }
    }
}