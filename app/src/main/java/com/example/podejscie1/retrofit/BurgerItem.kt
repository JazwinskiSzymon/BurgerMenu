package com.example.podejscie1.retrofit

data class BurgerItem(
    val addresses: List<Addresse>,
    val description: String,
    val id: Int,
    val ingredients: List<String>,
    val name: String,
    val restaurant: String,
    val web: String,
    var count: Int = 1
)