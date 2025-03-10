package com.example.podejscie1

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.podejscie1.databinding.ItemBurgerBinding
import kotlin.random.Random

class BurgerAdapter : RecyclerView.Adapter<BurgerAdapter.BurgerViewHolder>() {


    inner class BurgerViewHolder(val binding: ItemBurgerBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback =
        object : DiffUtil.ItemCallback<BurgerItem>() {
            override fun areItemsTheSame(oldItem: BurgerItem, newItem: BurgerItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: BurgerItem, newItem: BurgerItem): Boolean {
                return oldItem == newItem
            }
        }

    private val differ = AsyncListDiffer(this, diffCallback)
    var burgers: List<BurgerItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BurgerViewHolder {
        return BurgerViewHolder(
            ItemBurgerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = burgers.size


    override fun onBindViewHolder(holder: BurgerViewHolder, position: Int) {
        holder.binding.apply {
            val burger = burgers[position]
            tvBurgerName.text = burger.name
            tvDescription.text = burger.description
            ivBurger.setImageResource(R.drawable.placeholder)
            tvPrice.text = "${(String.format("%.2f",priceVal()))} zł"
        }
    }

    private fun priceVal(): Double {
        val pr1 = Random.nextDouble(30.0, 50.0).toInt()
        val pr2 = if (Random.nextBoolean()) 0.0 else 0.5
        return pr1 + pr2
    }

}