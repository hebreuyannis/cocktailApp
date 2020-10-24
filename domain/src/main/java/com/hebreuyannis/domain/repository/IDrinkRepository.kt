package com.hebreuyannis.domain.repository

import com.hebreuyannis.domain.models.Drink
import kotlinx.coroutines.flow.Flow
import com.hebreuyannis.domain.models.Result

interface IDrinkRepository {
    fun searchDrinks(name:String): Flow<Result<List<Drink>>>

    fun getRandomDrinks(): Flow<Result<List<Drink>>>
}