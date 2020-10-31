package com.hebreuyannis.cocktailapp.mappers

import com.hebreuyannis.cocktailapp.models.DrinkPresentation
import com.hebreuyannis.cocktailapp.models.IngredientPresentation
import com.hebreuyannis.domain.models.Drink
import com.hebreuyannis.domain.models.Ingredient


fun DrinkPresentation.toDomain(): Drink {
    return Drink(name, tag, category, alcoholic, typeGlass, instruction, drinkThumb, arrayListOf())
}

fun IngredientPresentation.toDomain(): Ingredient {
    return Ingredient(name, description, type, ingredientThumb, isAlcohol)
}