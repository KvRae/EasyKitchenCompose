package com.kvrae.easykitchen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kvrae.easykitchen.data.local.database.EasyKitchenDb
import com.kvrae.easykitchen.presentation.logic.CategoryViewModel
import com.kvrae.easykitchen.presentation.logic.IngredientViewModel
import com.kvrae.easykitchen.presentation.logic.MealsViewModel
import com.kvrae.easykitchen.ui.theme.EasyKitchenTheme
import com.kvrae.easykitchen.utils.App
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    val database by inject<EasyKitchenDb>()

    private val mealsViewModel by inject<MealsViewModel> ()

    private val ingredientViewModel by inject<IngredientViewModel> ()

    private val categoryViewModel by inject<CategoryViewModel> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            EasyKitchenTheme {
                App(
                    mealsViewModel = mealsViewModel,
                    ingredientsViewModel = ingredientViewModel,
                    categoryViewModel = categoryViewModel
                )
            }
        }
    }
}