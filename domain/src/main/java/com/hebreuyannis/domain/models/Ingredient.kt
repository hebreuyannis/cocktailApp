package com.hebreuyannis.domain.models

data class Ingredient(
    val name: String,
    val description: String,
    val type: String,
    val ingredientThumb: String,
    val isAlcohol: Boolean
)