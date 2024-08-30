package com.kvrae.easykitchen.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.kvrae.easykitchen.ui.components.BottomNavBar
import com.kvrae.easykitchen.ui.components.TopBar
import com.kvrae.easykitchen.utils.MAIN_COMPOSE_ROUTE
import com.kvrae.easykitchen.utils.MAIN_HOME_ROUTE
import com.kvrae.easykitchen.utils.MAIN_MEALS_ROUTE
import com.kvrae.easykitchen.utils.navItems

@Composable
fun MainScreen(navController: NavController) {
    var navItem by remember {
        mutableStateOf(navItems.first().name)
    }
    Log.d("NavItem", navItem)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {TopBar()},
        bottomBar = {
            BottomNavBar(
                navItems = navItems,
                navItem = navItem,
                onNavItemSelect = {
                    navItem = it
                }
            )
        },
        content = { paddingValues ->
            MainScreenContent(
                modifier = Modifier
                    .navigationBarsPadding()
                    .statusBarsPadding()
                    .padding(paddingValues),
                navItem = navItem,
                navController = navController
            )
        }
    )
}

@Composable
fun MainScreenContent(
    modifier: Modifier,
    navItem: String? = null,
    navController: NavController
) {
    when(navItem){
        MAIN_HOME_ROUTE -> HomeScreen(
            modifier = modifier,
            navController = navController
        )
        MAIN_MEALS_ROUTE -> MealsScreen(
            modifier = modifier,
            navController = navController
        )
        MAIN_COMPOSE_ROUTE -> IngredientsScreen(
            modifier = modifier,
            navController = navController
        )
        else -> HomeScreen(
            modifier = modifier,
            navController = navController
        )
    }
}


