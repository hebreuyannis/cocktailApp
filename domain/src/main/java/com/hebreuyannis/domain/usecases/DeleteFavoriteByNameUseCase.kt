package com.hebreuyannis.domain.usecases

import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.repository.IFavoriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DeleteFavoriteByNameUseCase(ioDispatcher: CoroutineDispatcher,
    private val favoritesRepository: IFavoriteRepository
) : FlowUseCase<String, Int>(ioDispatcher) {

    override fun execute(parameters: String): Flow<Result<Int>> {
        return favoritesRepository.deleteFavoriteByName(parameters)
            .map { observableResult: Result<Int> ->
                when (observableResult) {
                    is Result.Success -> Result.Success(observableResult.data)
                    is Result.Error -> Result.Error(observableResult.exception)
                    Result.Loading -> Result.Loading
                }
            }
    }
}