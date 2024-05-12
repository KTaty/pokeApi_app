package com.example.vk_test_intern.utils


fun String.extractId() = this.substringAfter("pokemon").replace("/", "").toInt()

fun String.getFormUrl(): String =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${this.extractId()}.png"
