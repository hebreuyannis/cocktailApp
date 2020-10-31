package com.hebreuyannis.data_local.repository

import com.hebreuyannis.data_local.dao.FavoriteDao
import com.hebreuyannis.data_local.mappers.toDomain
import com.hebreuyannis.domain.models.Favorite
import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.repository.IFavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoriteRepository(private val favoriteDao: FavoriteDao) : IFavoriteRepository {
    override fun getAllFavorites(): Flow<Result<List<Favorite>>> = flow {
        val favorites = favoriteDao.getAll()
        emit(Result.Success(favorites.map { it.toDomain() }))
    }

    override fun getFavoriteByName(name: String): Flow<Result<Favorite>> = flow {
        val favorite = favoriteDao.getByName(name)
        emit(Result.Success(favorite.toDomain()))
    }

    override fun deleteFavoriteByName(name: String): Flow<Result<Int>> = flow {
        val row = favoriteDao.deleteByName(name)
        emit(Result.Success(row))
    }

    override fun deleteAllFavorites(): Flow<Result<Int>> = flow {
        val row = favoriteDao.deleteAll()
        emit(Result.Success(row))
    }

    override fun insertFavorite(favorite: Favorite): Flow<Result<Int>> = flow {
        val result = favoriteDao.insert(favorite)
        emit(Result.Success(result))
    }
}