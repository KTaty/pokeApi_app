package com.example.vk_test_intern.feature.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vk_test_intern.R
import com.example.vk_test_intern.feature.model.Stats

class StatsListAdapter : RecyclerView.Adapter<StatsListItemVh>(){
    private val dataset: MutableList<Stats> = mutableListOf()

    fun setStats(stats: List<Stats>) {
        Log.e("pokeDebug"," set stats: $stats")
        dataset.clear()
        dataset.addAll(stats)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsListItemVh {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stats, parent, false)
        return StatsListItemVh(view)
    }

    override fun onBindViewHolder(holder: StatsListItemVh, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}