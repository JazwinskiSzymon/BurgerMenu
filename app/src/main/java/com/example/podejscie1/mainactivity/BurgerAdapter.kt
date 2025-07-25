package com.example.podejscie1.mainactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.example.podejscie1.retrofit.BurgerItem
import com.example.podejscie1.R
import com.example.podejscie1.databinding.ItemBurgerBinding

class BurgerAdapter(private val burgerViewModel: BurgerViewModel) :
    RecyclerView.Adapter<BurgerAdapter.BurgerViewHolder>() {

    inner class BurgerViewHolder(val binding: ItemBurgerBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<BurgerItem>() {
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
        val burger = burgers[position]
        val binding = holder.binding
        binding.apply {
            tvBurgerName.text = burger.name
            tvDescription.text = burger.description
            ivBurger.setImageResource(R.drawable.placeholder)
            tvPrice.text = "30"
            tvAmount.text = burger.count.toString()

            val isExpanded = burgerViewModel.expandedBurgerId.value == burger.id

            if (expandableLayout.visibility == View.VISIBLE && !isExpanded) {
                TransitionManager.beginDelayedTransition(holder.itemView as ViewGroup)
            }
            expandableLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE


            ivAdd.setOnClickListener {
                burgerViewModel.onBurgerToggled(burger.id)
            }

            btnAdd.setOnClickListener {
                burgerViewModel.incrementBurgerCount(burger.id)
            }
            btnRemove.setOnClickListener {
                burgerViewModel.decrementBurgerCount(burger.id)
            }
            btnAccept.setOnClickListener {
                val extraCheese = checkboxExtraCheese.isChecked
                val extraMeat = checkboxExtraMeat.isChecked
                val price = (tvPrice.text as String).toDouble()
                burgerViewModel.addBurgerToCart(burger, extraCheese, extraMeat, price)
            }
        }
    }
}