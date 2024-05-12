package com.example.vk_test_intern.feature.ui

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vk_test_intern.R
import com.example.vk_test_intern.feature.model.Stats
import com.example.vk_test_intern.utils.MAX_STAT

class StatsListItemVh(view: View) : RecyclerView.ViewHolder(view) {

    private val label: TextView = view.findViewById(R.id.stat_label)
    private val count: TextView = view.findViewById(R.id.stat_count)
    private val progress: ProgressBar = view.findViewById(R.id.stat_progress)

    fun bind(stats: Stats) {
        label.text = stats.stat.name
        count.text = "${stats.effort}/${stats.base_stat}"

        progress.progress = stats.base_stat

        progress.max = MAX_STAT
        //For the beauty of visualization, the maximum value = 100 is conventionally accepted. Clarifications are needed.
    }
}