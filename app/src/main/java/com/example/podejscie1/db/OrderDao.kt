package com.example.podejscie1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao {
    @Insert
    suspend fun insert(order: OrderItem)

    @Query("SELECT * FROM orders ORDER BY orderedAt DESC")
    fun getAllOrders(): LiveData<List<OrderItem>>
}