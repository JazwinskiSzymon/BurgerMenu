package com.example.podejscie1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.podejscie1.db.AppDatabase
import com.example.podejscie1.db.CartItem
import com.example.podejscie1.db.OrderItem
import kotlinx.coroutines.launch
import kotlin.random.Random

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val cartDao = db.cartDao()
    private val orderDao = db.orderDao()

    val cartItems: LiveData<List<CartItem>> = cartDao.getAllItems()

    fun addItem(item: CartItem) = viewModelScope.launch {
        cartDao.insert(item)
    }

    fun removeItem(item: CartItem) = viewModelScope.launch {
        cartDao.delete(item)
    }

    fun updateItem(item: CartItem) = viewModelScope.launch {
        cartDao.update(item)
    }

    fun clearCart() = viewModelScope.launch {
        cartDao.clearCart()
    }

    private fun generateNewOrderId(): Int {
        val newOrderId = Random.nextInt(1, 10001)
        return newOrderId
    }

    fun orderCart() = viewModelScope.launch {
        var orderId = generateNewOrderId()
        val items = cartDao.getAllItemsList()

        for (item in items) {
            val order = OrderItem(
                burgerId = item.burgerId.toString(),
                name = item.name,
                quantity = item.amount,
                extraCheese = item.extraCheese,
                extraMeat = item.extraMeat,
                orderId = orderId
            )
            orderDao.insert(order)
        }

        cartDao.clearCart()
    }

    fun markAllAsOrdered() = viewModelScope.launch {
        cartDao.markAllAsOrdered()
    }
}
