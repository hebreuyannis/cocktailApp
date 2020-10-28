@file:Suppress("BlockingMethodInNonBlockingContext")

package com.hebreuyannis.data_remote.repository

import com.hebreuyannis.data_remote.api.CocktailApiService
import com.hebreuyannis.data_remote.exception.LoadingException
import com.hebreuyannis.data_remote.exception.NetworkException
import com.hebreuyannis.data_remote.mappers.toArrayDomain
import com.hebreuyannis.domain.models.Drink
import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.repository.IDrinkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class DrinkRepository(private val apiService: CocktailApiService) : IDrinkRepository {
    override fun searchDrinks(name: String): Flow<Result<List<Drink>>> {
        return flow {
            emit(Result.Loading)
            val callable = apiService.searchDrinkByName(name)
            val response = callable.execute()
            if (!response.isSuccessful) {
                emit(Result.Error(NetworkException(response.code())))
            } else {
                if (response.body() == null || response.body()?.drinks.isNullOrEmpty()) {
                    emit(Result.Error(LoadingException()))
                } else {
                    emit(Result.Success(response.body()!!.toArrayDomain()))
                }
            }
        }
    }

    override fun getRandomDrinks(): Flow<Result<List<Drink>>> {
        val azerty = "azertyuiopqsdfghjklmwxcvbn"
        return flow {
            emit(Result.Loading)
            val charArray = azerty.toCharArray()
            val letter = charArray[Random.nextInt(0, charArray.size - 1)]
            val callable = apiService.searchDrinkByLetter(letter.toString())
            val response = callable.execute()
            if (!response.isSuccessful) {
                emit(Result.Error(NetworkException(response.code())))
            } else {
                if (response.body() == null || response.body()?.drinks.isNullOrEmpty()) {
                    emit(Result.Error(LoadingException()))
                } else {
                    emit(Result.Success(response.body()!!.toArrayDomain()))
                }
            }
        }
    }
}