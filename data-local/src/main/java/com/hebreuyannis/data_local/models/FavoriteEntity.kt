package com.hebreuyannis.data_local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "fav_id") val id: Int = 0,
    val name: String,
    @ColumnInfo(name = "tag") val tag: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "alcoholic") val alcoholic: String,
    @ColumnInfo(name = "type_glass") val typeGlass: String,
    @ColumnInfo(name = "instruction") val instruction: String,
    @ColumnInfo(name = "drink_thumb") val drinkThumb: String,
)