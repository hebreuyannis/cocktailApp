package com.hebreuyannis.domain.models

data class Favorite(
    val name: String,
    val tag: String,
    val category: String,
    val alcoholic: String,
    val typeGlass: String,
    val instruction: String,
    val drinkThumb: String,
    val ingredient: List<Ingredient>
)