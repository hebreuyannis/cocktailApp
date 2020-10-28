package com.hebreuyannis.cocktailapp.mappers

import com.hebreuyannis.cocktailapp.models.DrinkPresentation
import com.hebreuyannis.cocktailapp.models.IngredientPresentation
import com.hebreuyannis.domain.models.Drink
import com.hebreuyannis.domain.models.Ingredient

internal fun Drink.toPresentation(): DrinkPresentation {
    return DrinkPresentation(name, tag, category, alcoholic, typeGlass, instruction, drinkThumb)
}

internal fun Ingredient.toPresentation(): IngredientPresentation {
    return IngredientPresentation(name, description, type, ingredientThumb, isAlcohol)
}