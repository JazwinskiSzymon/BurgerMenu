package com.example.podejscie1.mainactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.podejscie1.secondacitivty.SecondActivity
import com.example.podejscie1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var burgerAdapter: BurgerAdapter

    private val burgerViewModel: BurgerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivLocation.setOnClickListener {
            openSecondActivity("location")
        }

        binding.ivCart.setOnClickListener {
            openSecondActivity("cart")
        }

        binding.ivProfile.setOnClickListener {
            openSecondActivity("profile")
        }

        setupRecyclerView()

        burgerViewModel.burgers.observe(this, Observer { burgers ->
            burgerAdapter.burgers = burgers
        })

        burgerViewModel.fetchBurgers()


    }


    private fun setupRecyclerView() = binding.rvBurgers.apply {
        burgerAdapter = BurgerAdapter(burgerViewModel)
        binding.rvBurgers.apply {
            adapter = burgerAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun openSecondActivity(buttonId: String) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("buttonId", buttonId)
        startActivity(intent)
    }
}
