package com.hebreuyannis.data_local.models

import androidx.room.Embedded
import androidx.room.Relation

class FavoriteIngredient(
    @Embedded
    val favoriteEntity: FavoriteEntity,
    @Relation(
        parentColumn = "fav_id",
        entityColumn = "id"
    )
    val ingredientEntities: List<IngredientEntity>
)