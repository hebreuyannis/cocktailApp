package com.hebreuyannis.domain.usecases

import com.hebreuyannis.domain.models.Favorite
import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.repository.IFavoriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllFavoriteUseCase(
    ioDispatcher: CoroutineDispatcher,
    private val favoriteRepository: IFavoriteRepository
) : FlowUseCase<Unit, List<Favorite>>(ioDispatcher) {

    override fun execute(parameters: Unit): Flow<Result<List<Favorite>>> {
        return favoriteRepository.getAllFavorites()
            .map { observableResult: Result<List<Favorite>> ->
                when (observableResult) {
                    is Result.Success -> Result.Success(observableResult.data)
                    is Result.Error -> Result.Error(observableResult.exception)
                    Result.Loading -> Result.Loading
                }
            }
    }
}