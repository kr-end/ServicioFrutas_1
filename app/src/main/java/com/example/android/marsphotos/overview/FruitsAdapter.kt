package com.example.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.model.Fruit

class FruitsAdapter :
    ListAdapter<Fruit, FruitsAdapter.FruitTextViewHolder>(DiffCallback) {

    class FruitTextViewHolder(private val binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fruit: Fruit) {
            val nutrition = fruit.nutritions

            val fruta = buildString {
                append("${fruit.name}\n")
                append("Id: ${fruit.id}\n")
                append("Family: ${fruit.family}\n")
                append("Order: ${fruit.order}\n")
                append("Genus: ${fruit.genus}\n")
                append("Calories: ${nutrition.calories}\n")
                append("Fat: ${nutrition.fat}\n")
                append("Sugar: ${nutrition.sugar}\n")
                append("Carbohydrates: ${nutrition.carbohydrates}\n")
                append("Protein: ${nutrition.protein}\n")
            }

            binding.fruitTxt.text = fruta
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Fruit>() {
        override fun areItemsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
            return oldItem.name == newItem.name

        }

        override fun areContentsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FruitTextViewHolder {
        return FruitTextViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: FruitTextViewHolder, position: Int) {
        val fruit = getItem(position)
        holder.bind(fruit)
    }
}
