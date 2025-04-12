package com.example.podejscie1.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val orderId: Int,
    val burgerId: String,
    val name: String,
    val quantity: Int,
    val extraCheese: Boolean,
    val extraMeat: Boolean,
    val orderedAt: Long = System.currentTimeMillis(),
    val status: String = "ordered"
)