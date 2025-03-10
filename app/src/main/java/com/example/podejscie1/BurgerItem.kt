package com.example.podejscie1

import kotlin.random.Random
import kotlin.random.Random.Default.nextDouble

data class BurgerItem(
    val addresses: List<Addresse>,
    val description: String,
    val id: Int,
    val ingredients: List<String>,
    val name: String,
    val restaurant: String,
    val web: String,
)