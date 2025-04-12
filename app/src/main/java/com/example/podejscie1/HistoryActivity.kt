package com.example.podejscie1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.podejscie1.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private val orderHistoryViewModel: OrderHistoryViewModel by viewModels() // Używamy nowego ViewModel dla historii zamówień

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvOrders.layoutManager = LinearLayoutManager(this)

        orderHistoryViewModel.getAllOrders().observe(this, Observer { orders ->
            val adapter = OrderHistoryAdapter(orders)
            binding.rvOrders.adapter = adapter
        })
    }
}
