package com.example.podejscie1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.podejscie1.db.AppDatabase
import com.example.podejscie1.db.CartDao
import com.example.podejscie1.db.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class BurgerViewModel(application: Application) : AndroidViewModel(application) {

    private val cartDao: CartDao = AppDatabase.getInstance(application).cartDao()

    private val _burgers = MutableLiveData<List<BurgerItem>>()
    val burgers: LiveData<List<BurgerItem>> get() = _burgers


    fun fetchBurgers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.getBurgers()
                if (response.isSuccessful) {
                    val burgersList = response.body()?.map {
                        it.copy(count = 1)
                    }
                    _burgers.postValue(burgersList ?: emptyList())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun generateNewOrderId(): Int {
        val newOrderId = Random.nextInt(1, 10001)
        return newOrderId
    }


    fun addBurgerToCart(burger: BurgerItem, extraCheese: Boolean, extraMeat: Boolean, amount: Int) {
        viewModelScope.launch {
            val existingItem = cartDao.getItemByBurger(burger.id, extraCheese, extraMeat)

            if (existingItem != null) {
                val updatedItem = existingItem.copy(amount = existingItem.amount + amount)
                viewModelScope.launch(Dispatchers.IO) {
                    cartDao.update(updatedItem)
                }
            } else {
                val cartItem = CartItem(
                    burgerId = burger.id,
                    name = burger.name,
                    extraCheese = extraCheese,
                    extraMeat = extraMeat,
                    amount = amount
                )
                viewModelScope.launch(Dispatchers.IO) {
                    cartDao.insert(cartItem)
                }
            }
        }
    }

    fun removeBurgerFromCart(burger: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            cartDao.delete(burger)
        }
    }

    fun updateBurgerInCart(burger: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            cartDao.update(burger)
        }
    }

}
