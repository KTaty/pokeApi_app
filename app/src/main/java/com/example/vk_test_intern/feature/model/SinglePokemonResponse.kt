package com.example.vk_test_intern.feature.model

data class SinglePokemonResponse(
    val id: Int,
    val height: Int,
    val weight: Int,
    val stats: List<Stats>,
)
