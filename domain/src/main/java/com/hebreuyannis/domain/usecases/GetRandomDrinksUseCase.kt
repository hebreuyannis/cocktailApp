package com.hebreuyannis.domain.usecases

import com.hebreuyannis.domain.models.Drink
import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.repository.IDrinkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRandomDrinksUseCase(
    ioDispatcher: CoroutineDispatcher,
    private val drinkRepository: IDrinkRepository
) : FlowUseCase<Unit, List<Drink>>(ioDispatcher) {

    override fun execute(parameters: Unit): Flow<Result<List<Drink>>> {
        return drinkRepository.getRandomDrinks()
            .map { observableResult: Result<List<Drink>> ->
                when (observableResult) {
                    is Result.Success -> Result.Success(observableResult.data)
                    is Result.Error -> Result.Error(observableResult.exception)
                    Result.Loading -> Result.Loading
                }
            }
    }
}