package com.example.vk_test_intern.feature.presentation

import com.example.vk_test_intern.feature.model.Stats

sealed interface SinglePokeAction {
    data class Init (val name: String): SinglePokeAction
    data class StateLoaded(val id: Int, val stats: List<Stats>) : SinglePokeAction
    data class LoadError(val error: String) : SinglePokeAction
}