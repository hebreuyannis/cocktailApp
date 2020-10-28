package com.hebreuyannis.domain.repository

import com.hebreuyannis.domain.models.Drink
import com.hebreuyannis.domain.models.Result
import kotlinx.coroutines.flow.Flow

interface IDrinkRepository {
    fun searchDrinks(name: String): Flow<Result<List<Drink>>>

    fun getRandomDrinks(): Flow<Result<List<Drink>>>
}