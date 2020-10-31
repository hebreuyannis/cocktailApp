package com.hebreuyannis.data_local.mappers

import com.hebreuyannis.data_local.models.FavoriteEntity
import com.hebreuyannis.data_local.models.IngredientEntity
import com.hebreuyannis.domain.models.Favorite
import com.hebreuyannis.domain.models.Ingredient

internal fun Favorite.toEntity(): FavoriteEntity {
    return FavoriteEntity(
        name = name,
        alcoholic = alcoholic,
        category = category,
        typeGlass = typeGlass,
        drinkThumb = drinkThumb,
        instruction = instruction,
        tag = tag
    )
}

internal fun Ingredient.toEntity(favId: Long): IngredientEntity {
    return IngredientEntity(
        name = name,
        description = description,
        ingredientThumb = ingredientThumb,
        type = type,
        isAlcohol = isAlcohol,
        favoriteId = favId
    )
}