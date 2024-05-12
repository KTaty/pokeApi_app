package com.example.vk_test_intern.feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.vk_test_intern.feature.api.PokeRepository
import com.example.vk_test_intern.feature.model.Pokemon
import kotlinx.coroutines.flow.Flow



class PokemonListViewModel : ViewModel() {
    private val pokeRepository = PokeRepository()


    private var currentResult: Flow<PagingData<Pokemon>>? = null

    fun getPokemons(): Flow<PagingData<Pokemon>> {
        val newResult: Flow<PagingData<Pokemon>> =
            pokeRepository.getPokemonFlow().cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }
}