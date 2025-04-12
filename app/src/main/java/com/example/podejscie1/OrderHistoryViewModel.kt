package com.example.podejscie1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.podejscie1.db.AppDatabase
import com.example.podejscie1.db.OrderItem

class OrderHistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val orderDao = AppDatabase.getInstance(application).orderDao()


    fun getAllOrders(): LiveData<List<OrderItem>> {
        return orderDao.getAllOrders()
    }

}
