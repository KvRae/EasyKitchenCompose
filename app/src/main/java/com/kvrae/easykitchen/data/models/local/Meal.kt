package com.kvrae.easykitchen.data.models.local

data class Meal(
    val id : Int,
    val name : String,
    val image : String,
    val category : String,
    val area : String,
    val instructions : String,
    val tags : String,
    val youtube : String,
    val source : String,
    val date : String,
    val ingredients : List<Ingredient>,

)
