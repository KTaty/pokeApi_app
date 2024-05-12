package com.example.vk_test_intern.core

import kotlin.reflect.KClass

object ViewModelProvider  {

    val featureStorage = mutableMapOf<KClass<*>, MVIFeature>()

    inline fun <reified T : MVIFeature> obtainFeature(noinline featureCreator: () -> T): T {
        return featureStorage.getOrPut(T::class) { featureCreator() } as T
    }

    fun <T : MVIFeature> destroyFeature(clazz: KClass<T>) {
        featureStorage.remove(clazz)
    }
}