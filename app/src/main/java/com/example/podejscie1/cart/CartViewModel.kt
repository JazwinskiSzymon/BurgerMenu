package com.example.podejscie1.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.podejscie1.db.AppDatabase
import com.example.podejscie1.db.CartItem
import com.example.podejscie1.db.OrderItem
import com.example.podejscie1.repository.CartRepository
import kotlinx.coroutines.launch
import kotlin.random.Random

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CartRepository
    val cartItems: LiveData<List<CartItem>>

    init {
        val db = AppDatabase.getInstance(application)
        repository = CartRepository(db.cartDao(), db.orderDao())
        cartItems = repository.cartItems
    }

    fun removeItem(item: CartItem) = viewModelScope.launch {
        repository.removeItem(item)
    }

    private fun generateNewOrderId(): Int {
        return Random.nextInt(1, 10001)
    }

    fun orderCart() = viewModelScope.launch {
        val orderId = generateNewOrderId()
        val items = repository.getCartItemsList()

        for (item in items) {
            val order = OrderItem(
                burgerId = item.burgerId.toString(),
                name = item.name,
                quantity = item.amount,
                extraCheese = item.extraCheese,
                extraMeat = item.extraMeat,
                orderId = orderId,
                price = item.price
            )
            repository.insertOrder(order)
        }

        repository.clearCart()
    }
}
