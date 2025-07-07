package com.example.podejscie1.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.podejscie1.R
import com.example.podejscie1.databinding.FragmentCartBinding

class CartFragment : Fragment(R.layout.fragment_cart) {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        setupRecyclerView()
        observeViewModel()

        binding.btnPay.setOnClickListener {
            if (cartViewModel.cartItems.value?.isNotEmpty() == true) {
                cartViewModel.orderCart()
                Toast.makeText(requireContext(), "Zamówienie złożone!", Toast.LENGTH_SHORT).show()
                activity?.finish()
            } else {
                Toast.makeText(requireContext(), "Koszyk jest pusty!", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(cartViewModel)
        binding.rvCart.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeViewModel() {
        cartViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            cartAdapter.cartItems = cartItems

            val totalPrice = cartItems.sumOf { it.price * it.amount }
            val totalPrice2 = totalPrice + 15.00 + 0.79
            binding.tvPrice1.text = String.format("%.2f zł", totalPrice)
            binding.tvPrice2.text = String.format("%.2f zł", totalPrice2)

            if (cartItems.isEmpty()) {
                binding.rvCart.visibility = View.GONE
            } else {
                binding.rvCart.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}