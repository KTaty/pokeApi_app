package com.example.vk_test_intern.feature.ui


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vk_test_intern.R
import com.example.vk_test_intern.feature.model.Pokemon
import com.example.vk_test_intern.utils.extractId
import com.example.vk_test_intern.utils.loadPokeForm
import java.util.Locale


class PokeListItemVh(view: View) : RecyclerView.ViewHolder(view) {

    private val form: ImageView = view.findViewById(R.id.pokemon_form)
    private val title: TextView = view.findViewById(R.id.name)

    fun bind(pokemon: Pokemon) {
        title.text = pokemon.name
        loadPokeForm(form, pokemon.url.extractId())
    }

}