package com.hebreuyannis.data_remote.mappers

import com.hebreuyannis.data_remote.models.DrinksResponse
import com.hebreuyannis.data_remote.models.IngredientResponse
import com.hebreuyannis.data_remote.models.IngredientsResponse
import com.hebreuyannis.domain.models.Drink
import com.hebreuyannis.domain.models.Ingredient
import java.util.*
import kotlin.collections.ArrayList

internal fun DrinksResponse.toDomain(): Drink {
    return Drink(
        name,
        tags,
        category,
        alcoholic,
        typeGlass,
        instruction,
        drinkThumb,
        getArrayIngredient()
    )
}

private fun DrinksResponse.getArrayIngredient(): List<String> {
    val ingredientList = ArrayList<String>()
    ingredient1?.let { ingredientList.add(it) }
    ingredient2?.let { ingredientList.add(it) }
    ingredient3?.let { ingredientList.add(it) }
    ingredient4?.let { ingredientList.add(it) }
    ingredient5?.let { ingredientList.add(it) }
    ingredient6?.let { ingredientList.add(it) }
    return ingredientList.toList()
}

internal fun IngredientsResponse.toDomain(): Ingredient {
    val ingredient = this.ingredients.first()
    return Ingredient(
        ingredient.name, ingredient.description, ingredient.type,
        getThumbnailIngredient(ingredient.name), ingredient.isAlcohol()
    )
}

private fun getThumbnailIngredient(name: String): String {
    return "https://www.thecocktaildb.com/images/ingredients/${name}-Small.png"
}

private fun IngredientResponse.isAlcohol() = this.alcohol.toLowerCase(Locale.ROOT) == "yes"
