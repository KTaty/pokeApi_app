package com.example.vk_test_intern.feature.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.vk_test_intern.feature.model.Pokemon
import java.io.IOException

class PokeDataSource(
    private val pokemonApi: PokeApi
) : PagingSource<Int, Pokemon>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val offset = params.key ?: 0
        val loadSize =  params.loadSize
        return try {
            val data = pokemonApi.getPokemonList(loadSize, offset)
            LoadResult.Page(
                data = data.results,
                prevKey = if (data.previous == null) null else offset - loadSize,
                nextKey = if (data.next == null) null else offset + loadSize
            )
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    LoadResult.Error(IOException("Please check internet connection"))
                }
                else -> {
                    LoadResult.Error(t)
                }
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition
    }
}