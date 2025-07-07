package com.example.podejscie1.mainactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.podejscie1.retrofit.BurgerItem
import com.example.podejscie1.retrofit.RetrofitInstance
import com.example.podejscie1.db.AppDatabase
import com.example.podejscie1.db.CartDao
import com.example.podejscie1.db.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BurgerViewModel(application: Application) : AndroidViewModel(application) {

    private val cartDao: CartDao = AppDatabase.getInstance(application).cartDao()

    private val _burgers = MutableLiveData<List<BurgerItem>>()
    val burgers: LiveData<List<BurgerItem>> get() = _burgers

    private val _expandedBurgerId = MutableLiveData<Int?>(null)
    val expandedBurgerId: LiveData<Int?> get() = _expandedBurgerId


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

    fun addBurgerToCart(burger: BurgerItem, extraCheese: Boolean, extraMeat: Boolean, price: Double) {
        viewModelScope.launch {
            val existingItem = cartDao.getItemByBurger(burger.id, extraCheese, extraMeat)

            if (existingItem != null) {
                val updatedItem = existingItem.copy(amount = existingItem.amount + burger.count)
                viewModelScope.launch(Dispatchers.IO) {
                    cartDao.update(updatedItem)
                }
            } else {
                val cartItem = CartItem(
                    burgerId = burger.id,
                    name = burger.name,
                    extraCheese = extraCheese,
                    extraMeat = extraMeat,
                    amount = burger.count,
                    price = price
                )
                viewModelScope.launch(Dispatchers.IO) {
                    cartDao.insert(cartItem)
                }
            }
        }
    }

    fun onBurgerToggled(burgerId: Int) {
        if (_expandedBurgerId.value == burgerId) {
            _expandedBurgerId.value = null
        } else {
            _expandedBurgerId.value = burgerId
        }
    }

    fun incrementBurgerCount(burgerId: Int) {
        val currentList = _burgers.value ?: return
        val newList = currentList.map { burger ->
            if (burger.id == burgerId) {
                burger.copy(count = burger.count + 1)
            } else {
                burger
            }
        }
        _burgers.value = newList
    }

    fun decrementBurgerCount(burgerId: Int) {
        val currentList = _burgers.value ?: return
        val newList = currentList.map { burger ->
            if (burger.id == burgerId && burger.count > 1) {
                burger.copy(count = burger.count - 1)
            } else {
                burger
            }
        }
        _burgers.value = newList
    }
}