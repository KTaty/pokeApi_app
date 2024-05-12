package com.example.vk_test_intern.feature.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.vk_test_intern.R
import com.example.vk_test_intern.feature.model.Pokemon

class PokePagingAdapter(
    private val onItemClick: (Pokemon) -> Unit
): PagingDataAdapter<Pokemon, PokeListItemVh>(PokemonDiffCallback()) {

    override fun onBindViewHolder(holder: PokeListItemVh, position: Int) {

        val data = getItem(position)!!
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onItemClick.invoke(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeListItemVh {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poke_list, parent, false)
        return PokeListItemVh(view)
    }

    private class PokemonDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }


}