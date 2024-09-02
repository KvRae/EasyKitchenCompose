package com.kvrae.easykitchen.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kvrae.easykitchen.R
import com.kvrae.easykitchen.data.models.remote.CategoryResponse
import com.kvrae.easykitchen.data.models.remote.IngredientResponse
import com.kvrae.easykitchen.data.models.remote.MealResponse
import com.kvrae.easykitchen.ui.components.BottomNavBar
import com.kvrae.easykitchen.ui.components.TopBar
import com.kvrae.easykitchen.utils.MAIN_COMPOSE_ROUTE
import com.kvrae.easykitchen.utils.MAIN_MEALS_ROUTE
import com.kvrae.easykitchen.utils.navItems
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavController,
    isNetworkOn: Boolean,
    mealResponses: List<MealResponse>,
    ingredientResponses : List<IngredientResponse>,
    categories : List<CategoryResponse>
) {

    var navItem by rememberSaveable {
        mutableStateOf(navItems.first().name)
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                ModalDrawerSheetContent()
            }
        },
    ) {
        MainScreenScaffold(
            meals = mealResponses,
            ingredients = ingredientResponses,
            categories = categories,
            isNetworkOn = isNetworkOn,
            navController = navController,
            navItem = navItem,
            onNavItemChange = {
                navItem = it
            },
            onMenuClick = {
                scope.launch {
                    Log.d("MainScreen Click", "Menu Clicked")
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            }
        )
    }
}

@Composable
fun MainScreenScaffold(
    meals: List<MealResponse>,
    ingredients : List<IngredientResponse>,
    categories : List<CategoryResponse>,
    context: Context = LocalContext.current,
    isNetworkOn: Boolean,
    modifier: Modifier = Modifier,
    navController: NavController,
    navItem: String,
    onNavItemChange: (String) -> Unit,
    onMenuClick: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val ingredientBasket = mutableListOf<String>()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = navItem,
                ingredientsSize = ingredientBasket.size,
                onActionClick = onMenuClick
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        content = { paddingValues ->
            paddingValues
            MainScreenNavigation(
                modifier = Modifier
                    .navigationBarsPadding()
                    .statusBarsPadding()
                    .padding(top = 56.dp, bottom = 68.dp),
                navItem = navItem,
                navController = navController,
                mealResponses = meals,
                categories = categories,
                ingredientResponses = ingredients,
                onIngredientClick = { ingredient ->
                    if (ingredientBasket.contains(ingredient)) {
                        ingredientBasket.remove(ingredient)
                    } else {
                        ingredientBasket.add(ingredient)
                    }
                }

            )
            DisposableEffect(key1 = isNetworkOn) {
                if (!isNetworkOn)
                    scope.launch {
                        snackBarHostState
                            .showSnackbar(
                                message = context
                                    .getString(R.string.no_internet_connection),
                                duration = SnackbarDuration.Indefinite

                            )
                    }
                onDispose {
                    scope.launch {
                        snackBarHostState.currentSnackbarData?.dismiss()
                    }
                }
            }
        },
        bottomBar = {
            BottomNavBar(
                navItems = navItems,
                navItem = navItem,
                onNavItemSelect = {
                    onNavItemChange(it)
                }
            )
        }
    )
}

@Composable
fun MainScreenNavigation(
    modifier: Modifier,
    navItem: String? = null,
    navController: NavController,
    onIngredientClick: (String) -> Unit,
    mealResponses: List<MealResponse>,
    ingredientResponses: List<IngredientResponse>,
    categories: List<CategoryResponse>
) {
    when(navItem){
        MAIN_MEALS_ROUTE -> MealsScreen(
            modifier = modifier,
            navController = navController,
            mealResponses = mealResponses
        )
        MAIN_COMPOSE_ROUTE -> IngredientsScreen(
            modifier = modifier,
            navController = navController,
            onIngredientClick = onIngredientClick,
            meals = mealResponses,
            ingredientResponses = ingredientResponses
        )
        else -> HomeScreen(
            modifier = modifier,
            navController = navController,
            mealResponses = mealResponses,
            categories = categories,

        )
    }
}

@Composable
fun ModalDrawerSheetContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(.6f)
    ) {
        Text(text = "Welcome to EasyKitchen")
        ListItem(
            headlineContent = { /*TODO*/ },
            trailingContent = { /*TODO*/ },
        )
    }


}


