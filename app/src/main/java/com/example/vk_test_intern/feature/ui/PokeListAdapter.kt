package com.example.vk_test_intern.feature.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vk_test_intern.R
import com.example.vk_test_intern.feature.model.Pokemon

class PokeListAdapter(
    private val onItemClick: (Pokemon) -> Unit
): RecyclerView.Adapter<PokeListItemVh>() {

    private val dataset: MutableList<Pokemon> = mutableListOf()

    fun setPokemons(pokemon: List<Pokemon>) {
        dataset.clear()
        dataset.addAll(pokemon)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeListItemVh {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poke_list, parent, false)
        return PokeListItemVh(view)
    }

    override fun onBindViewHolder(holder: PokeListItemVh, position: Int) {
        holder.bind(dataset[position])
        holder.itemView.setOnClickListener {
           onItemClick.invoke(dataset[position])
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}