package com.example.vk_test_intern.feature.presentation

class SinglePokeReducer {
    fun applyAction(action: SinglePokeAction, state: SinglePokeState): SinglePokeState {
        return when (action) {
            is SinglePokeAction.Init -> state.copy(pokemonName = action.name, isLoading = true)
            is SinglePokeAction.StateLoaded -> state.copy(pokemonID = action.id,stats = action.stats, isLoading = false)
            is SinglePokeAction.LoadError -> state.copy(error = action.error, isLoading = false)
        }
    }
}