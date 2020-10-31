package com.hebreuyannis.data_local.mappers

import com.hebreuyannis.data_local.models.FavoriteIngredient
import com.hebreuyannis.data_local.models.IngredientEntity
import com.hebreuyannis.domain.models.Favorite
import com.hebreuyannis.domain.models.Ingredient

internal fun IngredientEntity.toDomain(): Ingredient =
    Ingredient(name, description, type, ingredientThumb, isAlcohol)

internal fun FavoriteIngredient.toDomain(): Favorite =
    Favorite(
        name = favoriteEntity.name,
        tag = favoriteEntity.tag,
        instruction = favoriteEntity.instruction,
        drinkThumb = favoriteEntity.drinkThumb,
        typeGlass = favoriteEntity.typeGlass,
        category = favoriteEntity.category,
        alcoholic = favoriteEntity.alcoholic,
        ingredients = ingredientEntities.map { it.toDomain() }
    )