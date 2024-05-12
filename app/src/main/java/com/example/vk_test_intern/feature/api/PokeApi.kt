package com.example.vk_test_intern.feature.api

import com.example.vk_test_intern.feature.model.PokemonsResponse
import com.example.vk_test_intern.feature.model.SinglePokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): PokemonsResponse

    @GET("pokemon/{name}/")
    suspend fun getPokemonWithName(@Path("name") name: String): SinglePokemonResponse

}