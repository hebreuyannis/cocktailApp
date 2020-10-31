package com.hebreuyannis.data_local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val type: String,
    val isAlcohol: Boolean,
    val favoriteId: Long,
    @ColumnInfo(name = "ingredient_thumb") val ingredientThumb: String,
)