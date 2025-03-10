package com.example.podejscie1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.podejscie1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var burgerAdapter: BurgerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            val response = RetrofitInstance.api.getBurgers()
            burgerAdapter.burgers = response.body()!!
        }

    }


    private fun setupRecyclerView() = binding.rvBurgers.apply {
        burgerAdapter = BurgerAdapter()
        adapter = burgerAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

}