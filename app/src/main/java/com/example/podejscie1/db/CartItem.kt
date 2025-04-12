package com.example.podejscie1.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val burgerId: Int,
    val name: String,
    val extraCheese: Boolean,
    val extraMeat: Boolean,
    val amount: Int,
    val ordered: Boolean = false
)
