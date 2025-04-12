package com.example.podejscie1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CartItem)

    @Update
    suspend fun update(item: CartItem)

    @Delete
    suspend fun delete(item: CartItem)

    @Query("SELECT * FROM cart_items")
    fun getAllItems(): LiveData<List<CartItem>>

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

    @Query("SELECT * FROM cart_items")
    suspend fun getAllItemsList(): List<CartItem>

    @Query("SELECT * FROM cart_items WHERE ordered = 0")
    fun getActiveItems(): LiveData<List<CartItem>>

    @Query("SELECT * FROM cart_items WHERE burgerId = :burgerId AND extraCheese = :extraCheese AND extraMeat = :extraMeat LIMIT 1")
    suspend fun getItemByBurger(burgerId: Int, extraCheese: Boolean, extraMeat: Boolean): CartItem?

    @Query("UPDATE cart_items SET ordered = 1 WHERE ordered = 0")
    suspend fun markAllAsOrdered()

    @Query("SELECT * FROM cart_items WHERE ordered = 1")
    fun getOrderedItems(): LiveData<List<CartItem>>
}