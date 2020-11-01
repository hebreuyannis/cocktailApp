package com.hebreuyannis.cocktailapp

import com.hebreuyannis.domain.models.Drink
import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.repository.IDrinkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDrinkRepository :IDrinkRepository {
    override fun searchDrinks(name: String): Flow<Result<List<Drink>>> {
       return  flow { Result.Loading }
    }

    override fun getRandomDrinks(): Flow<Result<List<Drink>>> {
        return flow { Result.Loading }
    }

}