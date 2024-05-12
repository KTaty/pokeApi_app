package com.example.vk_test_intern.feature.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.vk_test_intern.core.MyRetrofit
import com.example.vk_test_intern.feature.model.Pokemon
import com.example.vk_test_intern.feature.model.SinglePokemonResponse
import com.example.vk_test_intern.utils.PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class PokeRepository {
    private val api: PokeApi by lazy { MyRetrofit.getClient().create(PokeApi::class.java) }

    suspend fun getPokemonAll(): List<Pokemon> {
        return api.getPokemonList(
            limit = 100000,
            offset = 0
        ).results
    }

    fun getPokemonFlow(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                PokeDataSource(api)
            }
        ).flow
    }

    suspend fun getSinglePokemon(name: String): SinglePokemonResponse {
        return api.getPokemonWithName(
            name = name,
        )
    }

}