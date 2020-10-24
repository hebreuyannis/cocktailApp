package com.hebreuyannis.domain.repository

import com.hebreuyannis.domain.models.Favorite
import kotlinx.coroutines.flow.Flow
import com.hebreuyannis.domain.models.Result

interface IFavoriteRepository {

    fun getAllFavorites():  Flow<Result<List<Favorite>>>

    fun getFavoriteByName(name:String):  Flow<Result<Favorite>>

    fun deleteFavoriteByName(name: String):  Flow<Result<Int>>

    fun deleteAllFavorites():  Flow<Result<Int>>

    fun insertFavorite(favorite: Favorite): Flow<Result<Int>>

}