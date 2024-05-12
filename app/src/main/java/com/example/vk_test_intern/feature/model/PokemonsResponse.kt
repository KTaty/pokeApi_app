package com.example.vk_test_intern.feature.model

data class PokemonsResponse (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>,
)
