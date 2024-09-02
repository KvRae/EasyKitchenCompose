package com.kvrae.easykitchen.utils

fun getMealTime(): String {
    val now = System.currentTimeMillis()
    val time = now % 86400000
    return when {
        time < 43200000 -> "Breakfast"
        time < 64800000 -> "Lunch"
        else -> "Dinner"
    }
}

fun getUserLocation(): String {
    return "New York"
}

fun getMealCategoryByTime(): String {
    val mealType = getMealTime()
    val breakfast = "Breakfast"
    val lunch = listOf("Lamb", "Pasta", "Chicken", "Beef", "Pork", "Goat").random()
    val dinner = listOf("Vegetarian", "Side", "Miscellaneous", "Seafood", "Starter", "Vegan").random()
    return when (mealType) {
        "Lunch" -> lunch
        "Dinner" -> dinner
        else -> breakfast
    }
}