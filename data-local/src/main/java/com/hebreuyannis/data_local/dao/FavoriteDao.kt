package com.hebreuyannis.data_local.dao

import androidx.room.*
import com.hebreuyannis.data_local.mappers.toEntity
import com.hebreuyannis.data_local.models.FavoriteEntity
import com.hebreuyannis.data_local.models.FavoriteIngredient
import com.hebreuyannis.data_local.models.IngredientEntity
import com.hebreuyannis.domain.models.Favorite

@Dao
interface FavoriteDao {

    @Query("DELETE FROM favorites")
    fun deleteAll(): Int

    @Query("DELETE FROM favorites WHERE name=:name")
    fun deleteByName(name: String): Int

    @Transaction
    @Query("SELECT * FROM favorites WHERE name=:name")
    fun getByName(name: String): FavoriteIngredient

    @Transaction
    @Query("SELECT * FROM favorites")
    fun getAll(): List<FavoriteIngredient>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoriteEntity: FavoriteEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ingredientEntity: IngredientEntity): Long

    @Transaction
    fun insert(favorite: Favorite): Int {
        val favId = insert(favorite.toEntity())
        for (ingredient in favorite.ingredients) {
            val ingredientEntity = ingredient.toEntity(favId)
            insert(ingredientEntity)
            return 0
        }
        return 1
    }
}