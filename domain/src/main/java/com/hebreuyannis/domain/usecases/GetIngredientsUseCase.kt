package com.hebreuyannis.domain.usecases

import com.hebreuyannis.domain.models.Ingredient
import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.repository.IDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetIngredientsUseCase(
    ioDispatcher: CoroutineDispatcher,
    private val detailRepository: IDetailRepository
) : FlowUseCase<List<String>, List<Ingredient>>(ioDispatcher) {

    override fun execute(parameters: List<String>): Flow<Result<List<Ingredient>>> {
        return detailRepository.getIngredients(parameters)
            .map { observableResult: Result<List<Ingredient>> ->
                when (observableResult) {
                    is Result.Success -> Result.Success(observableResult.data)
                    is Result.Error -> Result.Error(observableResult.exception)
                    Result.Loading -> Result.Loading
                }
            }
    }
}