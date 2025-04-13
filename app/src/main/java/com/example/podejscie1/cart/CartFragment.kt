package com.example.podejscie1.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.podejscie1.R
import com.example.podejscie1.databinding.FragmentCartBinding

class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        binding.tvPrice1.text = "0.00zł"

        Log.i("MYTAG", "Cart Fragment")

        setupRecyclerView()

        cartViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            val totalPrice = cartItems.sumOf { it.price * it.amount }
            val totalPrice2 = totalPrice + 15.00 + 0.79
            binding.tvPrice1.text = String.format("%.2f zł", totalPrice)
            binding.tvPrice2.text = String.format("%.2f zł", totalPrice2)
        }

        binding.btnPay.setOnClickListener {
            cartViewModel.orderCart()
            Toast.makeText(requireContext(), "Zamówienie złożone!", Toast.LENGTH_SHORT).show()
            setupRecyclerView()
            activity?.finish()
        }

        cartViewModel.cartItems.observe(viewLifecycleOwner, Observer { cartItems ->
            if (cartItems.isNotEmpty()) {
                cartAdapter.cartItems = cartItems
            }
        })


        return binding.root
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(cartViewModel)
        binding.rvCart.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}
