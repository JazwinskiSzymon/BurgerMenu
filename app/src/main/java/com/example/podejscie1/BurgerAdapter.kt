package com.example.podejscie1

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.example.podejscie1.databinding.ItemBurgerBinding
import kotlin.random.Random

class BurgerAdapter(private val burgerViewModel: BurgerViewModel) :
    RecyclerView.Adapter<BurgerAdapter.BurgerViewHolder>() {

    private var expandedPosition: Int? = null

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
            tvPrice.text = "${String.format("%.2f", priceVal())} zł"
            tvAmount.text = burger.count.toString()

            val isExpanded = expandedPosition == position
            expandableLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE

            ivAdd.setOnClickListener {
                TransitionManager.beginDelayedTransition(binding.root as ViewGroup)
                val previousExpandedPosition = expandedPosition
                if (isExpanded) {
                    expandedPosition = null
                    notifyItemChanged(position)
                } else {
                    expandedPosition = position
                    notifyItemChanged(position)
                    previousExpandedPosition?.let { notifyItemChanged(it) }
                }
            }

            var localCount = burger.count
            Log.i("MYTAG", burger.count.toString())
            tvAmount.text = localCount.toString()

            btnAdd.setOnClickListener {
                localCount++
                burger.count = localCount
                tvAmount.text = localCount.toString()
            }
            btnRemove.setOnClickListener {
                if (localCount > 1) {
                    localCount--
                    burger.count = localCount
                    tvAmount.text = localCount.toString()
                }
            }
            btnAccept.setOnClickListener {
                val extraCheese = checkboxExtraCheese.isChecked
                val extraMeat = checkboxExtraMeat.isChecked
                burgerViewModel.addBurgerToCart(burger, extraCheese, extraMeat, localCount)
            }
        }
    }

    private fun priceVal(): Double {
        val pr1 = Random.nextDouble(30.0, 50.0).toInt()
        val pr2 = if (Random.nextBoolean()) 0.0 else 0.5
        return pr1 + pr2
    }
}
