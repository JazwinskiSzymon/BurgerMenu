package com.example.podejscie1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.podejscie1.databinding.ItemOrderBinding
import com.example.podejscie1.db.OrderItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OrderHistoryAdapter(private val orders: List<OrderItem>) :
    RecyclerView.Adapter<OrderHistoryAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        val binding = holder.binding

        binding.tvOrderNumber.text = "Zamówienie #${order.orderId}"

        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val formattedDate = dateFormat.format(Date(order.orderedAt))
        binding.tvTime.text = formattedDate

        binding.ivBurger.setImageResource(R.drawable.placeholder)

        binding.btnTrack.setOnClickListener {
        }
    }

    override fun getItemCount() = orders.size
}
