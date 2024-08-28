package com.kvrae.easykitchen.ui.screens

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
import com.kvrae.easykitchen.ui.components.BottomNavBar
import com.kvrae.easykitchen.ui.components.TopBar
import com.kvrae.easykitchen.ui.components.navItems

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    var navItem by remember {
        mutableStateOf(navItems.first().name)
    }

    Scaffold(
        modifier = Modifier,
        content = { paddingValues ->
            HomeScreen(
                modifier = Modifier
                    .padding(paddingValues)
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .fillMaxSize()
            )
        },
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

    )

}

@Composable
fun MainScreenContent(
    modifier: Modifier,
    navItem: String? = null
) {
    when(navItem){
        "Home" -> HomeScreen(modifier = modifier)
        "Meals" -> MealsScreen(modifier = modifier)
        else -> HomeScreen(modifier = modifier)
    }
}
