package com.example.vk_test_intern.feature.presentation

import com.example.vk_test_intern.feature.model.Stats

data class SinglePokeState(
    val pokemonName: String = "",
    val pokemonID: Int = 0,
    val stats: List<Stats> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = true,
)