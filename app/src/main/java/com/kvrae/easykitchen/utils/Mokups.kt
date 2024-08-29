package com.kvrae.easykitchen.utils

import com.kvrae.easykitchen.R

data class NavItem(
    val name: String,
    val iconFilled: Int,
    val iconOutline: Int
)

val navItems = listOf(
    NavItem( "Home", R.drawable.baseline_home_filled,R.drawable.rounded_home_outlined),
    NavItem("Meals", R.drawable.round_fastfood__filled, R.drawable.outline_fastfood_round),
    NavItem("Ingredients", R.drawable.rice_bowl_filled, R.drawable.outline_rice_bowl)
)