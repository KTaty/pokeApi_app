package com.example.vk_test_intern.core

import com.example.vk_test_intern.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofit {
    private var retrofit: Retrofit? = null

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getClient(): Retrofit {
        return retrofit ?: buildRetrofit().also { retrofit = it }
    }
}