package com.hebreuyannis.data_local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hebreuyannis.data_local.dao.FavoriteDao
import com.hebreuyannis.data_local.models.FavoriteEntity
import com.hebreuyannis.data_local.models.IngredientEntity

@Database(
    entities = [FavoriteEntity::class, IngredientEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DrinkDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoriteDao
}