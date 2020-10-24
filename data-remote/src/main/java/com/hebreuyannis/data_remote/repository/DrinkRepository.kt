package com.hebreuyannis.data_remote.repository

import com.hebreuyannis.data_remote.api.CocktailApiService
import com.hebreuyannis.data_remote.mappers.toDomain
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
            val response = apiService.searchDrinkByName(name)
            emit(Result.Success(response.map { it.toDomain() }))
        }
    }

    override fun getRandomDrinks(): Flow<Result<List<Drink>>> {
        val azerty = "azertyuiopqsdfghjklmwxcvbn"
        return flow {
            emit(Result.Loading)
            val charArray = azerty.toCharArray()
            val letter = charArray[Random.nextInt(0, charArray.size - 1)]
            val response = apiService.searchDrinkByLetter(letter.toString())
            emit(Result.Success(response.map { it.toDomain() }))
        }
    }
}