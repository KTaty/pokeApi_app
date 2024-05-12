package com.example.vk_test_intern.feature.presentation

import com.example.vk_test_intern.feature.model.Stats

sealed class SinglePokeViewState {
    data object Loading : SinglePokeViewState()
    data class Error(val errorText: String) : SinglePokeViewState()
    data class StatsLoaded(val name: String, val id: Int, val statsList: List<Stats>) : SinglePokeViewState()
}