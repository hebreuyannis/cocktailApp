package com.hebreuyannis.domain.usecases

import com.hebreuyannis.domain.models.Favorite
import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.repository.IFavoriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoriteByName(
    ioDispatcher: CoroutineDispatcher,
    private val favoritesRepository: IFavoriteRepository
) : FlowUseCase<String, Favorite>(ioDispatcher) {

    override fun execute(parameters: String): Flow<Result<Favorite>> {
        return favoritesRepository.getFavoriteByName(parameters)
            .map { observableResult: Result<Favorite> ->
                when (observableResult) {
                    is Result.Success -> Result.Success(observableResult.data)
                    is Result.Error -> Result.Error(observableResult.exception)
                    Result.Loading -> Result.Loading
                }
            }
    }
}