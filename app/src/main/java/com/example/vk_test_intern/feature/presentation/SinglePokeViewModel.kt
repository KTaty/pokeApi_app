package com.example.vk_test_intern.feature.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_test_intern.feature.api.PokeRepository
import com.example.vk_test_intern.core.MVIFeature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SinglePokeViewModel :  MVIFeature, ViewModel() {
    private val pokeName: String? =null

    private val mutableViewStateFlow = MutableStateFlow<SinglePokeViewState>(SinglePokeViewState.Loading)
    val viewStateFlow: StateFlow<SinglePokeViewState> = mutableViewStateFlow.asStateFlow()
    private var state: SinglePokeState = SinglePokeState()

    private val reducer = SinglePokeReducer()
    private val pokeRepository = PokeRepository()

    fun submitAction(action: SinglePokeAction) {

        Log.e("pokeDebug", "action: $action")
        state = reducer.applyAction(action, state)

        val viewState = createViewState(state)
        mutableViewStateFlow.value = viewState

        when (action) {
            is SinglePokeAction.Init -> {
                loadPokemon(action.name)
            }
            is SinglePokeAction.StateLoaded,
            is SinglePokeAction.LoadError -> {
                Unit
            }
        }
    }

    private fun createViewState(state: SinglePokeState): SinglePokeViewState {
        return when {
            state.isLoading -> SinglePokeViewState.Loading
            !state.error.isNullOrBlank() -> SinglePokeViewState.Error(state.error)
            else -> SinglePokeViewState.StatsLoaded(name = state.pokemonName, id= state.pokemonID, statsList = state.stats)
        }
    }

    private fun loadPokemon(pokemonName: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    pokeRepository.getSinglePokemon(pokemonName)
                }
                submitAction(SinglePokeAction.StateLoaded(id = response.id, stats = response.stats))
            } catch (e: Exception) {
                submitAction(SinglePokeAction.LoadError(e.message ?: "FATAL"))
            }
        }
    }}