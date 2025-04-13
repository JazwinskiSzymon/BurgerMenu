package com.example.podejscie1.repository

import androidx.lifecycle.LiveData
import com.example.podejscie1.db.CartDao
import com.example.podejscie1.db.CartItem
import com.example.podejscie1.db.OrderDao
import com.example.podejscie1.db.OrderItem

class CartRepository(
    private val cartDao: CartDao,
    private val orderDao: OrderDao
) {

    val cartItems: LiveData<List<CartItem>> = cartDao.getAllItems()

    suspend fun removeItem(item: CartItem) {
        cartDao.delete(item)
    }

    suspend fun getCartItemsList(): List<CartItem> {
        return cartDao.getAllItemsList()
    }

    suspend fun insertOrder(order: OrderItem) {
        orderDao.insert(order)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }
}
